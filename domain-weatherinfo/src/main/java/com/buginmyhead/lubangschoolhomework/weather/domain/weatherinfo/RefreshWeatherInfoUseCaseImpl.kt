package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RefreshWeatherInfoUseCaseImpl @Inject constructor(
    private val threeDayWeatherInfoRepository: ReadOnlyRepository<ThreeDayWeatherInfo>,
    private val viewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
) : RefreshWeatherInfoUseCase {

    override fun invoke() {
        threeDayWeatherInfoRepository.value
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<ThreeDayWeatherInfo> {

                override fun onSubscribe(d: Disposable) {
                    viewController.switchToLoading(WeatherInfoLoading)
                }

                override fun onSuccess(t: ThreeDayWeatherInfo) {
                    viewController.switchToSuccess(t)
                }

                override fun onError(e: Throwable) {
                    viewController.switchToFailure(WeatherInfoFailure)
                }

            })
    }

}
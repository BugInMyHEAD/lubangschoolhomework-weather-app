package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewController
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RefreshWeatherInfoUseCaseImpl(
    private val weatherInfoRepository: ReadOnlyRepository<WeatherInfo>,
    private val viewController: ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
) : RefreshWeatherInfoUseCase {

    override fun invoke() {
        weatherInfoRepository.value
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<WeatherInfo> {

                override fun onSubscribe(d: Disposable) {
                    viewController.switchToLoading(WeatherInfoLoading)
                }

                override fun onSuccess(t: WeatherInfo) {
                    viewController.switchToSuccess(t)
                }

                override fun onError(e: Throwable) {
                    viewController.switchToFailure(WeatherInfoFailure)
                }

            })
    }

}
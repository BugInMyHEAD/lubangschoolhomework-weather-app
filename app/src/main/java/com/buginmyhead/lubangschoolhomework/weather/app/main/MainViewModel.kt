package com.buginmyhead.lubangschoolhomework.weather.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.ThreeDayWeatherInfoRepositoryImpl
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.OpenMeteo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshMainWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshMainWeatherInfoUseCaseImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.MainWeatherInfoViewControllerImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val refreshMainWeatherInfoUseCase: RefreshMainWeatherInfoUseCase,
    private val threeDayWeatherInfoOutput: Observable<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _threeDayWeatherInfoLiveData = MutableLiveData<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>().apply {
        compositeDisposable.add(threeDayWeatherInfoOutput.subscribe {
            postValue(it)
        })
    }
    val threeDayWeatherInfoLiveData: LiveData<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>
        get() = _threeDayWeatherInfoLiveData

    init {
        refreshWeatherInfo()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun refreshWeatherInfo() {
        refreshMainWeatherInfoUseCase()
    }

    companion object {

        private val threeDayWeatherInfoViewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure> =
            MainWeatherInfoViewControllerImpl()

        private val refreshMainWeatherInfoUseCase: RefreshMainWeatherInfoUseCase = RefreshMainWeatherInfoUseCaseImpl(
            threeDayWeatherInfoRepository = ThreeDayWeatherInfoRepositoryImpl(
                openMeteoRemoteDataSource = OpenMeteo.RemoteDataSourceImpl
            ),
            viewController = threeDayWeatherInfoViewController,
        )

        val factory = viewModelFactory {
            initializer {
                MainViewModel(
                    refreshMainWeatherInfoUseCase = refreshMainWeatherInfoUseCase,
                    threeDayWeatherInfoOutput = threeDayWeatherInfoViewController.output,
                )
            }
        }

    }

}
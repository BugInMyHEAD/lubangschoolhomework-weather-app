package com.buginmyhead.lubangschoolhomework.weather.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSourceDummyImpl
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRepositoryImpl
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCaseImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoViewControllerImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel(
    private val refreshWeatherInfoUseCase: RefreshWeatherInfoUseCase,
    private val weatherInfoOutput: Observable<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _weatherInfoLiveData = MutableLiveData<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>().apply {
        compositeDisposable.add(weatherInfoOutput.subscribe {
            postValue(it)
        })
    }
    val weatherInfoLiveData: LiveData<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>
        get() = _weatherInfoLiveData

    init {
        refreshWeatherInfo()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun refreshWeatherInfo() {
        refreshWeatherInfoUseCase()
    }

    companion object {

        private val weatherInfoViewController: ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> =
            WeatherInfoViewControllerImpl()

        private val refreshWeatherInfoUseCase: RefreshWeatherInfoUseCase = RefreshWeatherInfoUseCaseImpl(
            weatherInfoRepository = WeatherInfoRepositoryImpl(
                remoteDataSource = WeatherInfoRemoteDataSourceDummyImpl()
            ),
            viewController = weatherInfoViewController,
        )

        val factory = viewModelFactory {
            initializer {
                MainViewModel(
                    refreshWeatherInfoUseCase = refreshWeatherInfoUseCase,
                    weatherInfoOutput = weatherInfoViewController.output,
                )
            }
        }

    }

}
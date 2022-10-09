package com.buginmyhead.lubangschoolhomework.weather.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSourceDummyImpl
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRepositoryImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewData
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCaseImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoViewControllerImpl
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val weatherInfoViewController: ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> =
        WeatherInfoViewControllerImpl()

    private val refreshWeatherInfoUseCase: RefreshWeatherInfoUseCase = RefreshWeatherInfoUseCaseImpl(
        weatherInfoRepository = WeatherInfoRepositoryImpl(
            remoteDataSource = WeatherInfoRemoteDataSourceDummyImpl()
        ),
        viewController = weatherInfoViewController,
    )

    private val _weatherInfoLiveData = MutableLiveData<ViewData<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>().apply {
        compositeDisposable.add(weatherInfoViewController.state.subscribe {
            postValue(it)
        })
    }
    val weatherInfoLiveData: LiveData<ViewData<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>
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

}
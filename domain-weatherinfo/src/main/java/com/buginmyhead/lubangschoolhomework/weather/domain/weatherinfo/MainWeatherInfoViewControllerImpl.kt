package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import javax.inject.Inject

class MainWeatherInfoViewControllerImpl @Inject constructor(
) : AbstractViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>() {

    override val output: Subject<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> = BehaviorSubject.create()

}
package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.AbstractViewController
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewState
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

class WeatherInfoViewControllerImpl : AbstractViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>() {

    override val output: Subject<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> = BehaviorSubject.create()

}
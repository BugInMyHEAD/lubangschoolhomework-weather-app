package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewState
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class WeatherInfoViewControllerImpl : ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> {

    override val state: Subject<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> = PublishSubject.create()

}
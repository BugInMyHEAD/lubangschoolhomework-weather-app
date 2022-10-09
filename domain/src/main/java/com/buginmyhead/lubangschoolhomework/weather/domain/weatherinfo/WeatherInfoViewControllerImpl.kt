package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ViewData
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject

class WeatherInfoViewControllerImpl : ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> {

    override val state: Subject<ViewData<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> = PublishSubject.create()

}
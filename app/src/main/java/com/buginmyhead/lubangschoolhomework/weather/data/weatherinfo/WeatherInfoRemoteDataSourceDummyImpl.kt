package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class WeatherInfoRemoteDataSourceDummyImpl : WeatherInfoRemoteDataSource {

    override fun get(): Single<WeatherInfoRemoteDataSource.Dto> = Single
        .just(WeatherInfoRemoteDataSource.Dto())
        .delay(2, TimeUnit.SECONDS)

}
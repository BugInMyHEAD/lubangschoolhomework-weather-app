package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import io.reactivex.rxjava3.core.Single

class WeatherInfoRemoteDataSourceDummyImpl : WeatherInfoRemoteDataSource {

    override fun get(): Single<WeatherInfoRemoteDataSource.Dto> = Single.just(WeatherInfoRemoteDataSource.Dto())

}
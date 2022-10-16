package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Wgs84Coordinate
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import java.util.concurrent.TimeUnit

object WeatherInfoRemoteDataSourceDummyImpl : WeatherInfoRemoteDataSource {

    override fun get(
        dates: Iterable<LocalDate>,
        wgs84Coordinate: Wgs84Coordinate,
    ): Single<WeatherInfoRemoteDataSource.Dto> = Single
        .just(WeatherInfoRemoteDataSource.Dto())
        .delay(2, TimeUnit.SECONDS)

}
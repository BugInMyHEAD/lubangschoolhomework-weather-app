package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Wgs84Coordinate
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate

interface WeatherInfoRemoteDataSource {

    fun get(
        dates: Iterable<LocalDate>,
        wgs84Coordinate: Wgs84Coordinate,
    ): Single<Dto>

    data class Dto(
        val result: String? = null,
    )

}

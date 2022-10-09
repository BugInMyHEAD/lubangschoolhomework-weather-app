package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import io.reactivex.rxjava3.core.Single

interface WeatherInfoRemoteDataSource {

    fun get(): Single<Dto>

    data class Dto(
        val result: String? = null,
    )

}

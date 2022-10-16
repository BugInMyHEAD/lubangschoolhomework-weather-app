package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import java.time.LocalDate

object OpenMeteo {

    data class DailyWeatherForecastResponseDto(
        val currentWeather: CurrentWeatherDto? = null,
        val daily: DailyWeatherDto? = null,
    )

    data class CurrentWeatherDto(
        val temperature: Temperature? = null,
        val weatherCode: Int? = null,
    )

    data class DailyWeatherDto(
        val time: List<LocalDate> = emptyList(),
        val weatherCode: List<Int> = emptyList(),
        val apparentTemperatureMax: List<Float> = emptyList(),
        val apparentTemperatureMin: List<Float> = emptyList(),
        val precipitationSum: List<Float> = emptyList(),
    )

}
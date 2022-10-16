package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object OpenMeteo {

    @Serializable
    data class DailyWeatherForecastResponseDto(
        @SerialName("current_weather")
        val currentWeather: CurrentWeatherDto? = null,
        val daily: DailyWeatherDto? = null,
    )

    @Serializable
    data class CurrentWeatherDto(
        val temperature: Float? = null,
        @SerialName("weathercode")
        val weatherCode: Int? = null,
    )

    @Serializable
    data class DailyWeatherDto(
        val time: List<String> = emptyList(),
        @SerialName("weathercode")
        val weatherCode: List<Int> = emptyList(),
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: List<Float> = emptyList(),
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: List<Float> = emptyList(),
        @SerialName("precipitation_sum")
        val precipitationSum: List<Float> = emptyList(),
    )

}
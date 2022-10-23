package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Length
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature

data class WeatherInfo(
    val minTemperature: Temperature,
    val maxTemperature: Temperature,
    val precipitationSum: Length,
)

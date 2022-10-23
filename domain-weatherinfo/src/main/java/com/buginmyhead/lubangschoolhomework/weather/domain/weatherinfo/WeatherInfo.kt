package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Length
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature

data class WeatherInfo(
    val minTemperature: Temperature? = null,
    val maxTemperature: Temperature? = null,
    val precipitationSum: Length? = null,
)

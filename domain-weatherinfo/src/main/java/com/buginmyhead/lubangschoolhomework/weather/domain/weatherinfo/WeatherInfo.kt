package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature

data class WeatherInfo(
    val yesterdayMinTemperature: Temperature? = null,
    val yesterdayMaxTemperature: Temperature? = null,
    val todayMinTemperature: Temperature? = null,
    val todayMaxTemperature: Temperature? = null,
    val tomorrowMinTemperature: Temperature? = null,
    val tomorrowMaxTemperature: Temperature? = null,
    val rainfallProbability: Probability? = null,
)

package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temparature

data class WeatherInfo(
    val yesterdayMinTemperature: Temparature? = null,
    val yesterdayMaxTemperature: Temparature? = null,
    val todayMinTemperature: Temparature? = null,
    val todayMaxTemperature: Temparature? = null,
    val tomorrowMinTemperature: Temparature? = null,
    val tomorrowMaxTemperature: Temparature? = null,
    val rainfallProbability: Probability? = null,
)

package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temparature

data class WeatherInfo(
    val yesterdayTemperature: Temparature? = null,
    val todayTemperature: Temparature? = null,
    val rainfallProbability: Probability? = null,
)

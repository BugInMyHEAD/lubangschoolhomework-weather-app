package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.domain.fundamental.Temparature

data class WeatherInfo(
    val yesterdayTemperature: Temparature? = null,
    val todayTemperature: Temparature? = null,
    val rainfallProbability: Probability? = null,
)

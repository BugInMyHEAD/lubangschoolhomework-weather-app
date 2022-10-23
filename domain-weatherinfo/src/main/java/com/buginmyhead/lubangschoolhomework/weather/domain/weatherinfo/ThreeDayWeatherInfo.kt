package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

data class ThreeDayWeatherInfo(
    val yesterday: WeatherInfo,
    val today: WeatherInfo,
    val tomorrow: WeatherInfo,
)

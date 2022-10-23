package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

data class ThreeDayWeatherInfo(
    val yesterday: WeatherInfo = WeatherInfo(),
    val today: WeatherInfo = WeatherInfo(),
    val tomorrow: WeatherInfo = WeatherInfo(),
)

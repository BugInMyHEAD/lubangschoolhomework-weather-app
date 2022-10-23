package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.comparison

import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature

data class ThreeDayWeatherInfoComparison(
    val yesterdayAndToday: ApparentTemperatureComparison,
    val todayAndTomorrow: ApparentTemperatureComparison,
) {

    companion object {

        fun from(threeDayWeatherInfo: ThreeDayWeatherInfo) = ThreeDayWeatherInfoComparison(
            yesterdayAndToday = ApparentTemperatureComparison.from(
                Temperature.fromCelsius((threeDayWeatherInfo.yesterday.minTemperature.celsius + threeDayWeatherInfo.yesterday.maxTemperature.celsius) / 2),
                Temperature.fromCelsius((threeDayWeatherInfo.today.minTemperature.celsius + threeDayWeatherInfo.today.maxTemperature.celsius) / 2)
            ),
            todayAndTomorrow = ApparentTemperatureComparison.from(
                Temperature.fromCelsius((threeDayWeatherInfo.today.minTemperature.celsius + threeDayWeatherInfo.today.maxTemperature.celsius) / 2),
                Temperature.fromCelsius((threeDayWeatherInfo.tomorrow.minTemperature.celsius + threeDayWeatherInfo.tomorrow.maxTemperature.celsius) / 2)
            ),
        )

    }

}
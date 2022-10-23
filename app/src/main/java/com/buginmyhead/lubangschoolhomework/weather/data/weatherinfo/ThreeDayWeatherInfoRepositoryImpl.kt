package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Length.Companion.millimeters
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import javax.inject.Inject

class ThreeDayWeatherInfoRepositoryImpl @Inject constructor(
    private val openMeteoRemoteDataSource: OpenMeteo.RemoteDataSource,
) : ReadOnlyRepository<ThreeDayWeatherInfo> {

    override val value: Single<ThreeDayWeatherInfo> get() = openMeteoRemoteDataSource
        .get(createOpenMeteoDailyWeatherForecastRequestParams())
        .map { it.toDomainModel() }

    private fun createOpenMeteoDailyWeatherForecastRequestParams(): OpenMeteo.DailyWeatherForecastRequestParams {
        val now = LocalDate.now()
        return OpenMeteo.DailyWeatherForecastRequestParams(
            latitude = 37.5139F,
            longitude = 126.9828F,
            startDate = now.minusDays(1),
            endDate = now.plusDays(1),
        )
    }

    private fun OpenMeteo.DailyWeatherForecastResponseDto.toDomainModel() = ThreeDayWeatherInfo(
        yesterday = WeatherInfo(
            minTemperature = daily?.apparentTemperatureMin?.get(0)?.let(Temperature::fromCelsius),
            maxTemperature = daily?.apparentTemperatureMax?.get(0)?.let(Temperature::fromCelsius),
            precipitationSum = daily?.precipitationSum?.get(0)?.millimeters,
        ),
        today = WeatherInfo(
            minTemperature = daily?.apparentTemperatureMin?.get(1)?.let(Temperature::fromCelsius),
            maxTemperature = daily?.apparentTemperatureMax?.get(1)?.let(Temperature::fromCelsius),
            precipitationSum = daily?.precipitationSum?.get(1)?.millimeters,
        ),
        tomorrow = WeatherInfo(
            minTemperature = daily?.apparentTemperatureMin?.get(2)?.let(Temperature::fromCelsius),
            maxTemperature = daily?.apparentTemperatureMax?.get(2)?.let(Temperature::fromCelsius),
            precipitationSum = daily?.precipitationSum?.get(2)?.millimeters,
        ),
    )

}
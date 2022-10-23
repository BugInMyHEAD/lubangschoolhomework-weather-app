package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import javax.inject.Inject

class ThreeDayWeatherInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherInfoRemoteDataSource
) : ReadOnlyRepository<ThreeDayWeatherInfo> {

    override val value: Single<ThreeDayWeatherInfo> get() = OpenMeteo.RemoteDataSource
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
        yesterdayMinTemperature = daily?.apparentTemperatureMin?.get(0)?.let(Temperature::fromCelsius),
        yesterdayMaxTemperature = daily?.apparentTemperatureMax?.get(0)?.let(Temperature::fromCelsius),
        todayMinTemperature = daily?.apparentTemperatureMin?.get(1)?.let(Temperature::fromCelsius),
        todayMaxTemperature = daily?.apparentTemperatureMax?.get(1)?.let(Temperature::fromCelsius),
        tomorrowMinTemperature = daily?.apparentTemperatureMin?.get(2)?.let(Temperature::fromCelsius),
        tomorrowMaxTemperature = daily?.apparentTemperatureMax?.get(2)?.let(Temperature::fromCelsius),
        rainfallProbability = Probability.orNull(0.5F),
    )

}
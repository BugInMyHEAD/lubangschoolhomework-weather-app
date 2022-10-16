package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Wgs84Coordinate
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import javax.inject.Inject

class WeatherInfoRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherInfoRemoteDataSource
) : ReadOnlyRepository<WeatherInfo> {

    override val value: Single<WeatherInfo> get() = remoteDataSource
        .get(
            dates = LocalDate.now().oneDayBackAndForth(),
            wgs84Coordinate = Wgs84Coordinate(37.5139F, 126.9828F),
        )
        .map { it.toDomainModel() }

    private fun LocalDate.oneDayBackAndForth(): Iterable<LocalDate> = listOf(
        minusDays(1), this, plusDays(1)
    )

    private fun WeatherInfoRemoteDataSource.Dto.toDomainModel() = WeatherInfo(
        yesterdayMinTemperature = Temperature(0F),
        yesterdayMaxTemperature = Temperature(10F),
        todayMinTemperature = Temperature(0F),
        todayMaxTemperature = Temperature(10F),
        tomorrowMinTemperature = Temperature(0F),
        tomorrowMaxTemperature = Temperature(10F),
        rainfallProbability = Probability.orNull(0.5F),
    )

}
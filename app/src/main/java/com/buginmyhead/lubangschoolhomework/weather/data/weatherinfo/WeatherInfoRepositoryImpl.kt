package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.domain.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.domain.fundamental.Temparature
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import io.reactivex.rxjava3.core.Single

class WeatherInfoRepositoryImpl(
    private val remoteDataSource: WeatherInfoRemoteDataSource,
) : ReadOnlyRepository<WeatherInfo> {

    override val value: Single<WeatherInfo> get() = remoteDataSource.get().map { it.toDomainModel() }

    private fun WeatherInfoRemoteDataSource.Dto.toDomainModel() = WeatherInfo(
        yesterdayTemperature = Temparature(0F),
        todayTemperature = Temparature(0F),
        rainfallProbability = Probability.orNull(0.5F),
    )

}
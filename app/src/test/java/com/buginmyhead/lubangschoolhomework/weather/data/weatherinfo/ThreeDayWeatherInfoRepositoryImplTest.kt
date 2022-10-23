package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class ThreeDayWeatherInfoRepositoryImplTest {

    private val remoteDataSource: WeatherInfoRemoteDataSource = mockk()

    private lateinit var threeDayWeatherInfoRepository: ReadOnlyRepository<ThreeDayWeatherInfo>

    @Before
    fun setUp() {
        threeDayWeatherInfoRepository = ThreeDayWeatherInfoRepositoryImpl(
            remoteDataSource = remoteDataSource,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify value`() {
        every {
            remoteDataSource.get(
                dates = any(),
                wgs84Coordinate = any(),
            )
        } returns Single.just(WeatherInfoRemoteDataSource.Dto())

        val testObserver = threeDayWeatherInfoRepository.value.test()

        testObserver.assertValue(ThreeDayWeatherInfo(
            yesterdayMinTemperature = Temperature.fromCelsius(0F),
            yesterdayMaxTemperature = Temperature.fromCelsius(10F),
            todayMinTemperature = Temperature.fromCelsius(0F),
            todayMaxTemperature = Temperature.fromCelsius(10F),
            tomorrowMinTemperature = Temperature.fromCelsius(0F),
            tomorrowMaxTemperature = Temperature.fromCelsius(10F),
            rainfallProbability = Probability.orNull(0.5F),
        ))
    }

}
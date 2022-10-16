package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Probability
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class WeatherInfoRepositoryImplTest {

    private val remoteDataSource: WeatherInfoRemoteDataSource = mockk()

    private lateinit var weatherInfoRepository: ReadOnlyRepository<WeatherInfo>

    @Before
    fun setUp() {
        weatherInfoRepository = WeatherInfoRepositoryImpl(
            remoteDataSource = remoteDataSource,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify value`() {
        every { remoteDataSource.get() } returns Single.just(WeatherInfoRemoteDataSource.Dto())

        val testObserver = weatherInfoRepository.value.test()

        testObserver.assertValue(WeatherInfo(
            yesterdayMinTemperature = Temperature(0F),
            todayMinTemperature = Temperature(0F),
            rainfallProbability = Probability.orNull(0.5F),
        ))
    }

}
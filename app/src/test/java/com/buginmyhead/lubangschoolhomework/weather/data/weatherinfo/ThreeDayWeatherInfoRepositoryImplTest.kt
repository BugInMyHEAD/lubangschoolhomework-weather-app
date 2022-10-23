package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Length.Companion.millimeters
import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class ThreeDayWeatherInfoRepositoryImplTest {

    private val openMeteoRemoteDataSource: OpenMeteo.RemoteDataSource = mockk()

    private lateinit var threeDayWeatherInfoRepository: ReadOnlyRepository<ThreeDayWeatherInfo>

    @Before
    fun setUp() {
        threeDayWeatherInfoRepository = ThreeDayWeatherInfoRepositoryImpl(
            openMeteoRemoteDataSource = openMeteoRemoteDataSource,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify value`() {
        every {
            openMeteoRemoteDataSource.get(any())
        } returns Single.just(OpenMeteo.DailyWeatherForecastResponseDto(
            daily = OpenMeteo.DailyWeatherDto(
                apparentTemperatureMin = listOf(10F, 20F, -10F),
                apparentTemperatureMax = listOf(20F, 30F, 0F),
                precipitationSum = listOf(0F, 20F, 10F)
            )
        ))

        val testObserver = threeDayWeatherInfoRepository.value.test()

        testObserver.assertValue(ThreeDayWeatherInfo(
            yesterday = WeatherInfo(
                minTemperature = Temperature.fromCelsius(10F),
                maxTemperature = Temperature.fromCelsius(20F),
                precipitationSum = 0F.millimeters,
            ),
            today = WeatherInfo(
                minTemperature = Temperature.fromCelsius(20F),
                maxTemperature = Temperature.fromCelsius(30F),
                precipitationSum = 20F.millimeters,
            ),
            tomorrow = WeatherInfo(
                minTemperature = Temperature.fromCelsius(-10F),
                maxTemperature = Temperature.fromCelsius(0F),
                precipitationSum = 10F.millimeters,
            ),
        ))
    }

}
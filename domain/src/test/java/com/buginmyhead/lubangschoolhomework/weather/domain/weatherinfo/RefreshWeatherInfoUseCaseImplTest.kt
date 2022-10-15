package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import io.reactivex.rxjava3.core.Single

import org.junit.After
import org.junit.Before
import org.junit.Test

class RefreshWeatherInfoUseCaseImplTest {

    private val weatherInfoRepository: ReadOnlyRepository<WeatherInfo> = mockk()
    private val viewController: ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure> = mockk(relaxUnitFun = true)

    private lateinit var refreshWeatherInfoUseCase: RefreshWeatherInfoUseCase

    @Before
    fun setUp() {
        refreshWeatherInfoUseCase = RefreshWeatherInfoUseCaseImpl(
            weatherInfoRepository = weatherInfoRepository,
            viewController = viewController,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify success`() {
        val weatherInfo = WeatherInfo()
        every { weatherInfoRepository.value } returns Single.just(weatherInfo)

        refreshWeatherInfoUseCase()

        verifyOrder {
            viewController.switchToLoading(WeatherInfoLoading)
            viewController.switchToSuccess(weatherInfo)
        }
    }

    @Test
    fun `verify failure`() {
        every { weatherInfoRepository.value } returns Single.error(Exception())

        refreshWeatherInfoUseCase()

        verifyOrder {
            viewController.switchToLoading(WeatherInfoLoading)
            viewController.switchToFailure(WeatherInfoFailure)
        }
    }

}
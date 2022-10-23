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

class RefreshThreeDayWeatherInfoUseCaseImplTest {

    private val threeDayWeatherInfoRepository: ReadOnlyRepository<ThreeDayWeatherInfo> = mockk()
    private val viewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure> = mockk(relaxUnitFun = true)

    private lateinit var refreshWeatherInfoUseCase: RefreshWeatherInfoUseCase

    @Before
    fun setUp() {
        refreshWeatherInfoUseCase = RefreshWeatherInfoUseCaseImpl(
            threeDayWeatherInfoRepository = threeDayWeatherInfoRepository,
            viewController = viewController,
        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `verify success`() {
        val threeDayWeatherInfo = ThreeDayWeatherInfo()
        every { threeDayWeatherInfoRepository.value } returns Single.just(threeDayWeatherInfo)

        refreshWeatherInfoUseCase()

        verifyOrder {
            viewController.switchToLoading(WeatherInfoLoading)
            viewController.switchToSuccess(threeDayWeatherInfo)
        }
    }

    @Test
    fun `verify failure`() {
        every { threeDayWeatherInfoRepository.value } returns Single.error(Exception())

        refreshWeatherInfoUseCase()

        verifyOrder {
            viewController.switchToLoading(WeatherInfoLoading)
            viewController.switchToFailure(WeatherInfoFailure)
        }
    }

}
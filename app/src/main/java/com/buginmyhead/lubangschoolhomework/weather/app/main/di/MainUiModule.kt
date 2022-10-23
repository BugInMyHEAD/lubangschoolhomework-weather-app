package com.buginmyhead.lubangschoolhomework.weather.app.main.di

import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Observable


@Module
@InstallIn(ViewModelComponent::class)
object MainUiModuleProvider {

    @Provides
    @ViewModelScoped
    fun provideWeatherInfoOutput(
        viewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
    ): Observable<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> =
        viewController.output

}
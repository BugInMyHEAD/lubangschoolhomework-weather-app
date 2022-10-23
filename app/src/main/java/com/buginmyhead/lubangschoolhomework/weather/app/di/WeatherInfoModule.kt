package com.buginmyhead.lubangschoolhomework.weather.app.di

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.OpenMeteo
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSource
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSourceDummyImpl
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.ThreeDayWeatherInfoRepositoryImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCaseImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.MainWeatherInfoViewControllerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Observable
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ThreeDayWeatherInfoModuleBinder {

    @Binds
    @Singleton
    fun bindThreeDayWeatherInfoRepository(
        impl: ThreeDayWeatherInfoRepositoryImpl
    ): ReadOnlyRepository<ThreeDayWeatherInfo>

    @Binds
    @Singleton
    fun bindMainWeatherInfoViewController(
        impl: MainWeatherInfoViewControllerImpl
    ): ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>

    @Binds
    @Singleton
    fun bindRefreshWeatherInfoUseCase(
        impl: RefreshWeatherInfoUseCaseImpl
    ): RefreshWeatherInfoUseCase

}

@Module
@InstallIn(SingletonComponent::class)
object WeatherInfoModuleProvider {

    @Provides
    @Singleton
    fun provideOpenMeteoRemoteDataSource(): OpenMeteo.RemoteDataSource = OpenMeteo.RemoteDataSourceImpl

}

@Module
@InstallIn(ViewModelComponent::class)
object WeatherInfoModuleViewModelProvider {

    @Provides
    @ViewModelScoped
    fun provideWeatherInfoOutput(
        viewController: ViewController<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
    ): Observable<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> =
        viewController.output

}
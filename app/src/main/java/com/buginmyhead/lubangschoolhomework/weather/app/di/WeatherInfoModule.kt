package com.buginmyhead.lubangschoolhomework.weather.app.di

import com.buginmyhead.lubangschoolhomework.weather.architecture.ReadOnlyRepository
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewController
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSource
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRemoteDataSourceDummyImpl
import com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo.WeatherInfoRepositoryImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshWeatherInfoUseCaseImpl
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoViewControllerImpl
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
interface WeatherInfoModuleBinder {

    @Binds
    @Singleton
    fun bindWeatherInfoRepository(
        impl: WeatherInfoRepositoryImpl
    ): ReadOnlyRepository<WeatherInfo>

    @Binds
    @Singleton
    fun bindWeatherInfoViewController(
        impl: WeatherInfoViewControllerImpl
    ): ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>

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
    fun provideWeatherInfoRemoteDataSource(): WeatherInfoRemoteDataSource = WeatherInfoRemoteDataSourceDummyImpl

}

@Module
@InstallIn(ViewModelComponent::class)
object WeatherInfoModuleViewModelProvider {

    @Provides
    @ViewModelScoped
    fun provideWeatherInfoOutput(
        viewController: ViewController<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>
    ): Observable<ViewState<WeatherInfo, WeatherInfoLoading, WeatherInfoFailure>> =
        viewController.output

}
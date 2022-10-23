package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.reactivex.rxjava3.core.Single
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

object OpenMeteo {

    data class DailyWeatherForecastRequestParams(
        val latitude: Float,
        val longitude: Float,
        val startDate: LocalDate,
        val endDate: LocalDate,
    ) {

        val paramMap: Map<String, String> = mapOf(
            "latitude" to latitude.toString(),
            "longitude" to longitude.toString(),
            "start_date" to startDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
            "end_date" to endDate.format(DateTimeFormatter.ISO_LOCAL_DATE),
        )

    }

    @Serializable
    data class DailyWeatherForecastResponseDto(
        @SerialName("current_weather")
        val currentWeather: CurrentWeatherDto? = null,
        val daily: DailyWeatherDto? = null,
    )

    @Serializable
    data class CurrentWeatherDto(
        val temperature: Float? = null,
        @SerialName("weathercode")
        val weatherCode: Int? = null,
    )

    @Serializable
    data class DailyWeatherDto(
        val time: List<String> = emptyList(),
        @SerialName("weathercode")
        val weatherCode: List<Int> = emptyList(),
        @SerialName("apparent_temperature_min")
        val apparentTemperatureMin: List<Float> = emptyList(),
        @SerialName("apparent_temperature_max")
        val apparentTemperatureMax: List<Float> = emptyList(),
        @SerialName("precipitation_sum")
        val precipitationSum: List<Float> = emptyList(),
    )

    interface RemoteDataSource {

        fun get(params: DailyWeatherForecastRequestParams): Single<DailyWeatherForecastResponseDto>

    }

    object RemoteDataSourceImpl : RemoteDataSource {

        private val json = Json {
            ignoreUnknownKeys = true
        }

        @OptIn(ExperimentalSerializationApi::class)
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        private val dailyWeatherForecastService: DailyWeatherForecastService = retrofit.create()

        private interface DailyWeatherForecastService {

            @GET("forecast?daily=weathercode,apparent_temperature_max,apparent_temperature_min,precipitation_sum&current_weather=true&timezone=auto")
            fun get(@QueryMap paramMap: Map<String, String>): Single<DailyWeatherForecastResponseDto>

        }

        override fun get(params: DailyWeatherForecastRequestParams): Single<DailyWeatherForecastResponseDto> =
            dailyWeatherForecastService.get(params.paramMap)

    }

}
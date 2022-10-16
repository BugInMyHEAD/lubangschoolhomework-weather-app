package com.buginmyhead.lubangschoolhomework.weather.data.weatherinfo

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class OpenMeteoDtoDeserializationTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun `test deserialization`() {
        val jsonString = """
            {
                "latitude": 37.5,
                "longitude": 127.0,
                "generationtime_ms": 2.7489662170410156,
                "utc_offset_seconds": 32400,
                "timezone": "Asia/Seoul",
                "timezone_abbreviation": "KST",
                "elevation": 4.0,
                "current_weather": {
                    "temperature": 15.2,
                    "windspeed": 2.3,
                    "winddirection": 72.0,
                    "weathercode": 3,
                    "time": "2022-10-16T02:00"
                },
                "daily_units": {
                    "time": "iso8601",
                    "weathercode": "wmo code",
                    "apparent_temperature_max": "°C",
                    "apparent_temperature_min": "°C",
                    "precipitation_sum": "mm"
                },
                "daily": {
                    "time": [
                        "2022-10-14",
                        "2022-10-15",
                        "2022-10-16",
                        "2022-10-17",
                        "2022-10-18"
                    ],
                    "weathercode": [
                        45,
                        45,
                        80,
                        3,
                        0
                    ],
                    "apparent_temperature_max": [
                        21.2,
                        22.4,
                        20.2,
                        14.8,
                        8.8
                    ],
                    "apparent_temperature_min": [
                        9.5,
                        11.7,
                        15.0,
                        3.5,
                        2.0
                    ],
                    "precipitation_sum": [
                        0.00,
                        0.00,
                        0.90,
                        0.00,
                        0.00
                    ]
                }
            }
        """.trimIndent()

        val expectedDto = OpenMeteo.DailyWeatherForecastResponseDto(
            currentWeather = OpenMeteo.CurrentWeatherDto(
                temperature = 15.2F,
                weatherCode = 3,
            ),
            daily = OpenMeteo.DailyWeatherDto(
                time = listOf(
                    "2022-10-14",
                    "2022-10-15",
                    "2022-10-16",
                    "2022-10-17",
                    "2022-10-18",
                ),
                weatherCode = listOf(
                    45,
                    45,
                    80,
                    3,
                    0,
                ),
                apparentTemperatureMax = listOf(
                    21.2F,
                    22.4F,
                    20.2F,
                    14.8F,
                    8.8F,
                ),
                apparentTemperatureMin = listOf(
                    9.5F,
                    11.7F,
                    15.0F,
                    3.5F,
                    2.0F,
                ),
                precipitationSum = listOf(
                    0.00F,
                    0.00F,
                    0.90F,
                    0.00F,
                    0.00F,
                )
            )
        )

        val actualDto = json.decodeFromString<OpenMeteo.DailyWeatherForecastResponseDto>(jsonString)

        Assert.assertEquals(expectedDto, actualDto)
    }

}
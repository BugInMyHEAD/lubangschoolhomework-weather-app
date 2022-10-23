package com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.comparison

import com.buginmyhead.lubangschoolhomework.weather.fundamental.Temperature

enum class ApparentTemperatureComparison {

    COLDER_THAN_BEFORE, COOLER_THAN_BEFORE, SIMILAR_TO_EACH_OTHER, WARMER_THAN_BEFORE, HOTTER_THAN_BEFORE;

    companion object {

        fun from(before: Temperature, next: Temperature): ApparentTemperatureComparison {
            val diff = next.celsius - before.celsius
            return when {
                diff >= 5F -> HOTTER_THAN_BEFORE
                diff <= -5F -> COLDER_THAN_BEFORE
                diff >= 2F -> if (before.celsius <= 24F) WARMER_THAN_BEFORE else HOTTER_THAN_BEFORE
                diff <= -2F -> if (before.celsius >= 18F) COOLER_THAN_BEFORE else COLDER_THAN_BEFORE
                else -> SIMILAR_TO_EACH_OTHER
            }
        }

    }

}
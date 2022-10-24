package com.buginmyhead.lubangschoolhomework.weather.app

import android.content.Context
import android.content.res.Resources
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.comparison.ApparentTemperatureComparison

class FromResources(val resources: Resources) {

    fun getStringBy(apparentTemperatureComparison: ApparentTemperatureComparison): String = resources.getString(
        when (apparentTemperatureComparison) {
            ApparentTemperatureComparison.COLDER_THAN_BEFORE -> R.string.colder_than_before
            ApparentTemperatureComparison.COOLER_THAN_BEFORE -> R.string.cooler_than_before
            ApparentTemperatureComparison.SIMILAR_TO_EACH_OTHER -> R.string.similar_to_each_other
            ApparentTemperatureComparison.WARMER_THAN_BEFORE -> R.string.warmer_than_before
            ApparentTemperatureComparison.HOTTER_THAN_BEFORE -> R.string.hotter_than_before
        }
    )

}

fun FromResources(context: Context) = FromResources(context.resources)
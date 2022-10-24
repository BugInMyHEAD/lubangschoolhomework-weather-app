package com.buginmyhead.lubangschoolhomework.weather.app

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.buginmyhead.lubangschoolhomework.weather.architecture.Loading
import com.buginmyhead.lubangschoolhomework.weather.architecture.ViewState
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.RefreshMainWeatherInfoUseCase
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.ThreeDayWeatherInfo
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoFailure
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.WeatherInfoLoading
import com.buginmyhead.lubangschoolhomework.weather.domain.weatherinfo.comparison.ThreeDayWeatherInfoComparison
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/**
 * Implementation of App Widget functionality.
 */
@AndroidEntryPoint
class ThreeDayWeatherInfoWidgetProvider : AppWidgetProvider() {

    @Inject
    internal lateinit var refreshMainWeatherInfoUseCase: RefreshMainWeatherInfoUseCase

    @Inject
    internal lateinit var weatherInfoOutput: Observable<ViewState<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure>>


    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        goAsync()
        refreshMainWeatherInfoUseCase()
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val fromResources = FromResources(context)

        weatherInfoOutput.blockingSubscribe {

            val views = RemoteViews(context.packageName, R.layout.three_day_weather_info_widget)

            val viewStatePresenter = object : ViewState.Presenter<ThreeDayWeatherInfo, WeatherInfoLoading, WeatherInfoFailure> {

                override fun onSuccess(data: ThreeDayWeatherInfo) {
                    views.setTextViewText(
                        R.id.appwidget_text,
                        fromResources.getStringBy(ThreeDayWeatherInfoComparison.from(data).yesterdayAndToday)
                    )
                }

                override fun onLoading(data: WeatherInfoLoading) {
                    views.setTextViewText(R.id.appwidget_text, "Loading")
                }

                override fun onFailure(data: WeatherInfoFailure) {
                    views.setTextViewText(R.id.appwidget_text, "Failure")
                }

            }

            views.setOnClickPendingIntent(
                android.R.id.content,
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE, null, context, javaClass),
                    PendingIntent.FLAG_IMMUTABLE
                )
            )

            it.showState(viewStatePresenter)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

}
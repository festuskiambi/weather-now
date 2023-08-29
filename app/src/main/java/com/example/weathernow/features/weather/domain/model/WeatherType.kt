package com.example.weathernow.features.weather.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.example.weathernow.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val backgroundRes: Int,
    @ColorRes val backgroundColor: Int,
) {
    object Rain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.rain_3x,
        backgroundRes = R.drawable.forest_rainy,
        backgroundColor = R.color.rainy
    )

    object Clear : WeatherType(
        weatherDesc = "Sunny",
        iconRes = R.drawable.clear_3x,
        backgroundRes = R.drawable.forest_sunny,
        backgroundColor = R.color.clear
    )

    object Cloudy : WeatherType(
        weatherDesc = "Cloudy",
        iconRes = R.drawable.partlysunny_3x,
        backgroundRes = R.drawable.forest_cloudy,
        backgroundColor = R.color.cloudy
    )
}

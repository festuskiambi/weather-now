package com.example.weathernow.features.weather.domain.model

import androidx.annotation.DrawableRes
import com.example.weathernow.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
) {
    object Rain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.rain_3x,
    )
    object Clear : WeatherType(
        weatherDesc = "Sunny",
        iconRes = R.drawable.clear_3x,
    )
    object Cloudy : WeatherType(
        weatherDesc = "Cloudy",
        iconRes = R.drawable.partlysunny_3x,
    )
}

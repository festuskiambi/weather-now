package com.example.weathernow.features.weather.domain.model

data class WeatherUiModel(
    val currTemp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val weatherType: WeatherType,
)

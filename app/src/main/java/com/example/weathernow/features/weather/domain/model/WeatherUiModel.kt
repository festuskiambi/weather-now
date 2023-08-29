package com.example.weathernow.features.weather.domain.model

data class Weather(
    val currTemp: Int,
    val date: String,
    val minTemp: Int,
    val maxTemp: Int,
    val weatherType: WeatherType,
)

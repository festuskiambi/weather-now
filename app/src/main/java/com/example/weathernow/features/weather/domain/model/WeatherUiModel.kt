package com.example.weathernow.features.weather.domain.model

data class Weather(
    val currTemp: Int? = null,
    val date: String? = null,
    val minTemp: Int? = null,
    val maxTemp: Int? = null,
    val weatherType: WeatherType?= null,
)

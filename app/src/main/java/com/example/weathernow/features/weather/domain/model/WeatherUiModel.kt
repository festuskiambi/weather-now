package com.example.weathernow.features.weather.domain.model

data class Weather(
    val currTemp: Double? = null,
    val date: String? = null,
    val minTemp: Double? = null,
    val maxTemp: Double? = null,
    val weatherType: WeatherType?= null,
)

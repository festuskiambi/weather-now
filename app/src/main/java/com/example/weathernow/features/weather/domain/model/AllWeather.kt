package com.example.weathernow.features.weather.domain.model

data class AllWeather(
    val currentWeather: Weather? = null,
    val fiveDayForecast: List<Weather> = emptyList(),
)
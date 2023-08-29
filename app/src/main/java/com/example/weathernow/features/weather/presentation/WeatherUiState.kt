package com.example.weathernow.features.weather.presentation

import com.example.weathernow.features.weather.domain.model.Weather

data class WeatherUiState(
    val isLoading: Boolean? = false,
    val currentWeather: Weather? = null,
    val fiveDayForecast: List<Weather> = emptyList(),
)
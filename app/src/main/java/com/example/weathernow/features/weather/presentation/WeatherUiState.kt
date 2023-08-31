package com.example.weathernow.features.weather.presentation

import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.util.UiText

data class WeatherUiState(
    val isLoading: Boolean? = false,
    val currentWeather: Weather? = null,
    val userMessage : UiText? = null,
    val fiveDayForecast: List<Weather> = emptyList(),
)
package com.example.weathernow.features.weather.presentation

import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.util.UserMessage

data class WeatherUiState(
    val allWeather: AllWeather? = null,
    val isLoading: Boolean? = false,
    val userMessage: UserMessage? = null,
)
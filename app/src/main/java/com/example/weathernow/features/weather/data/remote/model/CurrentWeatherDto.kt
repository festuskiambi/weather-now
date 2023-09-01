package com.example.weathernow.features.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    @SerialName("main")
    val mainDto: MainDto?,
    @SerialName("weather")
    val weatherDto: List<WeatherDto?>?,
)
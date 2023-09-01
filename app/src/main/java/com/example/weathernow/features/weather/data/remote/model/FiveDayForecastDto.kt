package com.example.weathernow.features.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FiveDayForecastDto(
    @SerialName("list")
    val list: List<DayWeatherDto>?,
)
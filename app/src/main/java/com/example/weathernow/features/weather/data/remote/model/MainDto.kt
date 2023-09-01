package com.example.weathernow.features.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MainDto(
    @SerialName("temp")
    val temp: Double?,
    @SerialName("temp_max")
    val tempMax: Double?,
    @SerialName("temp_min")
    val tempMin: Double?
)
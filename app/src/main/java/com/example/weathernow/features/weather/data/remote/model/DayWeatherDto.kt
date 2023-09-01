package com.example.weathernow.features.weather.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayWeatherDto(
    val dt: Int?,
    @SerialName("dt_txt")
    val dtTxt: String?,
    @SerialName("main")
    val mainDto: MainDto?,
    @SerialName("weather")
    val weatherDto: List<WeatherDto>?,
)
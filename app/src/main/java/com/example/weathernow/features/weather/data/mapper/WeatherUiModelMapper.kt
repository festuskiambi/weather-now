package com.example.weathernow.features.weather.data.mapper

import com.example.weathernow.features.weather.data.remote.model.CurrentWeatherDto
import com.example.weathernow.features.weather.data.remote.model.DayWeatherDto
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.features.weather.domain.model.WeatherType

fun CurrentWeatherDto.toWeatherUiModel() = Weather(
    currTemp = mainDto?.temp,
    date = null,
    minTemp = mainDto?.tempMin,
    maxTemp = mainDto?.tempMax,
    weatherType = when (weatherDto?.firstOrNull()?.main) {
        "Rain" -> WeatherType.Rain
        "Clouds" -> WeatherType.Cloudy
        else -> WeatherType.Clear
    }
)

fun DayWeatherDto.toWeatherUiModel() = Weather(
    currTemp = mainDto?.temp,
    date = dtTxt,
    minTemp = mainDto?.tempMin,
    maxTemp = mainDto?.tempMax,
    weatherType = when (weatherDto?.firstOrNull()?.main) {
        "Rain" -> WeatherType.Rain
        "Clouds" -> WeatherType.Cloudy
        else -> WeatherType.Clear
    }
)


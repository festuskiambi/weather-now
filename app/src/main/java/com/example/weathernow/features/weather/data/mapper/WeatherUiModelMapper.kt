package com.example.weathernow.features.weather.data.mapper

import com.example.weathernow.features.weather.data.remote.model.CurrentWeatherDto
import com.example.weathernow.features.weather.data.remote.model.DayWeatherDto
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.features.weather.domain.model.WeatherType
import com.example.weathernow.util.DAY_NAME_IN_WEEK_PATTERN
import com.example.weathernow.util.dateToString

fun CurrentWeatherDto.toWeatherUiModel() = Weather(
    currTemp = mainDto?.temp?.toInt(),
    date = null,
    minTemp = mainDto?.tempMin?.toInt(),
    maxTemp = mainDto?.tempMax?.toInt(),
    weatherType = when (weatherDto?.firstOrNull()?.main) {
        "Rain" -> WeatherType.Rain
        "Clouds" -> WeatherType.Cloudy
        else -> WeatherType.Clear
    }
)

fun DayWeatherDto.toWeatherUiModel() = Weather(
    currTemp = mainDto?.temp?.toInt(),
    date = dtTxt?.let {
        dateToString(
            pattern = DAY_NAME_IN_WEEK_PATTERN,
            dateString = it
        )
    },
    minTemp = mainDto?.tempMin?.toInt(),
    maxTemp = mainDto?.tempMax?.toInt(),
    weatherType = when (weatherDto?.firstOrNull()?.main) {
        "Rain" -> WeatherType.Rain
        "Clouds" -> WeatherType.Cloudy
        else -> WeatherType.Clear
    }
)


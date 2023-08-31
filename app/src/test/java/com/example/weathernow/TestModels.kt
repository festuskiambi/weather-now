package com.example.weathernow

import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.features.weather.domain.model.WeatherType

val testWeather = Weather(
    currTemp = 24,
    date = "29-08-2023",
    minTemp = 16,
    maxTemp = 27,
    weatherType = WeatherType.Cloudy
)

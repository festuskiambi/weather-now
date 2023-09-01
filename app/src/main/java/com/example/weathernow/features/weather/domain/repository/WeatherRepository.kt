package com.example.weathernow.features.weather.domain.repository

import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.util.Result

interface WeatherRepository {
    suspend fun getWeather(coordinate: Coordinate): Result<AllWeather>
}
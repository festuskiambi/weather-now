package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import com.example.weathernow.util.Result
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
): WeatherRepository {
    override suspend fun getWeather(): Result<AllWeather> {

    }
}
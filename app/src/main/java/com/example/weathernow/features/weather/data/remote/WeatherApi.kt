package com.example.weathernow.features.weather.data.remote

import com.example.weathernow.features.weather.data.remote.model.CurrentWeatherDto
import com.example.weathernow.features.weather.data.remote.model.FiveDayForecastDto

interface WeatherApi {
    suspend fun getCurrentWeather(): CurrentWeatherDto
    suspend fun getFiveDayForeCast(): FiveDayForecastDto

    companion object{
        const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org"

    }

}

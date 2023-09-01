package com.example.weathernow.features.weather.fake

import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import com.example.weathernow.testWeather
import com.example.weathernow.util.Result

class FakeWeatherRepository : WeatherRepository {
    override suspend fun getWeather(coordinate: Coordinate): Result<AllWeather> {
        return Result.Success(
            AllWeather(
                currentWeather = testWeather,
                fiveDayForecast = listOf(testWeather)
            )
        )
    }
}
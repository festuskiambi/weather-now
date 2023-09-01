package com.example.weathernow.features.weather.domain.useCase

import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(coordinate: Coordinate) =
        weatherRepository.getWeather(coordinate)
}
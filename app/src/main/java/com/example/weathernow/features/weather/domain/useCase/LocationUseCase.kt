package com.example.weathernow.features.weather.domain.useCase

import com.example.weathernow.features.weather.domain.repository.LocationRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository,
) {
    suspend operator fun invoke() =
        locationRepository.getCurrentCoordinate()
}
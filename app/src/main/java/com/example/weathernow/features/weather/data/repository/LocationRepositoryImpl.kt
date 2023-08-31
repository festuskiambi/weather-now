package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.LocationRepository
import com.google.android.gms.location.FusedLocationProviderClient
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val client: FusedLocationProviderClient,
):LocationRepository {
    override suspend fun getCurrentCoordinate(): Result<Coordinate> {
        TODO("Not yet implemented")
    }
}
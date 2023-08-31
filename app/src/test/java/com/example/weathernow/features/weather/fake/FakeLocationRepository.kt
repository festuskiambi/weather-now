package com.example.weathernow.features.weather.fake

import com.example.weathernow.coordinate1
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.LocationRepository
import com.example.weathernow.util.Result

class FakeLocationRepository : LocationRepository {
    override suspend fun getCurrentCoordinate(): Result<Coordinate> {
        return Result.Success(coordinate1)
    }
}
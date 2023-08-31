package com.example.weathernow.features.weather.domain.repository

import com.example.weathernow.features.weather.domain.model.Coordinate



interface LocationRepository {

    suspend fun getCurrentCoordinate(): Result<Coordinate>

}
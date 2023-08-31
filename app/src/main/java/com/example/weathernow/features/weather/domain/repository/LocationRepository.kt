package com.example.weathernow.features.weather.domain.repository

import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.util.Result




interface LocationRepository {
    suspend fun getCurrentCoordinate(): Result<Coordinate>

}
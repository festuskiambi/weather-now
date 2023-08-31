package com.example.weathernow.features.weather.data.repository

import android.content.Context
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.LocationRepository
import com.example.weathernow.util.Result
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val context: Context,
    private val defaultDispatcher: CoroutineDispatcher,
    ):LocationRepository {
    override suspend fun getCurrentCoordinate(): Result<Coordinate> {
        TODO("Not yet implemented")
    }
}
package com.example.weathernow.features.weather.data.repository

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.LocationRepository
import com.example.weathernow.util.LocationException
import com.example.weathernow.util.Result
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.Tasks
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val client: FusedLocationProviderClient,
    private val context: Context,
    private val defaultDispatcher: CoroutineDispatcher,
) : LocationRepository {
    override suspend fun getCurrentCoordinate(): Result<Coordinate> =
        withContext(defaultDispatcher) {
            val hasAccessFineLocationPermission =
                context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED
            val hasAccessCoarseLocationPermission =
                context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            if (!isGpsEnabled) return@withContext Result.Error(
                LocationException.LocationServiceDisabledException
            )

            if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission) {
                return@withContext Result.Error(LocationException.LocationPermissionDeniedException)
            }

            Log.d("repo", hasAccessFineLocationPermission.toString())
            Log.d("repo", hasAccessCoarseLocationPermission.toString())


            val lastLocation = Tasks.await(client.lastLocation)
//            Result.Success(Coordinate(-1.29987, 36.7724))
            when {
                lastLocation != null -> Result.Success(lastLocation.toCoordinate())
                else -> Result.Error(LocationException.NullLastLocation)
            }
        }


    private fun android.location.Location.toCoordinate(): Coordinate {
        return Coordinate(latitude = latitude, longitude = longitude)
    }
}
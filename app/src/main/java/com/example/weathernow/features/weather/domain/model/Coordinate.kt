package com.example.weathernow.features.weather.domain.model

import java.util.Locale

fun Double.roundTo(n: Int): Double = String.format(Locale.US, "%.${n}f", this).toDouble()
private const val DECIMAL_DEGREE_PRECISION = 2

class Coordinate(latitude: Double, longitude: Double) {
    val latitude: Double = latitude
        get() = if (field != 0.0) field.roundTo(DECIMAL_DEGREE_PRECISION) else 0.0

    val longitude: Double = longitude
        get() = if (field != 0.0) field.roundTo(DECIMAL_DEGREE_PRECISION) else 0.0
}
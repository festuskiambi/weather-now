package com.example.weathernow.features.weather.data.remote

import com.example.weathernow.features.weather.data.remote.model.CurrentWeatherDto
import com.example.weathernow.features.weather.data.remote.model.FiveDayForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(CURRENT_WEATHER_URL)
    suspend fun getCurrentWeather(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String? = APP_ID,
        @Query("units") units: String? = "metric",
    ): CurrentWeatherDto

    @GET(FIVE_DAY_FORECAST_URL)
    suspend fun getFiveDayForeCast(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String? = APP_ID,
        @Query("units") units: String? = "metric",
    ): FiveDayForecastDto

    companion object {
        const val OPEN_WEATHER_BASE_URL = "https://api.openweathermap.org"
        const val CURRENT_WEATHER_URL = "/data/2.5/weather"
        const val FIVE_DAY_FORECAST_URL = "/data/2.5/forecast"
        const val APP_ID = "966d242aa2032eceb2a1cbd87bc0c1a8"
    }
}

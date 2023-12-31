package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.features.weather.data.mapper.toWeatherUiModel
import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import com.example.weathernow.util.DAY_DATE_PATTERN
import com.example.weathernow.util.Result
import com.example.weathernow.util.dateToString
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi,
) : WeatherRepository {
    override suspend fun getWeather(coordinate: Coordinate): Result<AllWeather> {
        val currentWeather = api.getCurrentWeather(
            lat = coordinate.latitude.toString(),
            lon = coordinate.longitude.toString()
        )

        val fiveDayForecastDto = api.getFiveDayForeCast(
            lat = coordinate.latitude.toString(),
            lon = coordinate.longitude.toString()
        )

        val list = fiveDayForecastDto.dayWeatherDtoList?.distinctBy {
            it.dtTxt?.let { date ->
                dateToString(
                    pattern = DAY_DATE_PATTERN,
                    dateString = date
                )
            }
        }?.takeLast(5)

        return Result.Success(
            AllWeather(
                currentWeather = currentWeather.toWeatherUiModel(),
                fiveDayForecast = list?.map { it.toWeatherUiModel() }
            )
        )
    }
}
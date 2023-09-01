package com.example.weathernow

import com.example.weathernow.features.weather.data.remote.model.CurrentWeatherDto
import com.example.weathernow.features.weather.data.remote.model.DayWeatherDto
import com.example.weathernow.features.weather.data.remote.model.FiveDayForecastDto
import com.example.weathernow.features.weather.data.remote.model.MainDto
import com.example.weathernow.features.weather.data.remote.model.WeatherDto
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.features.weather.domain.model.WeatherType

val testWeather = Weather(
    currTemp = 24.00,
    date = "29-08-2023",
    minTemp = 16.00,
    maxTemp = 27.00,
    weatherType = WeatherType.Cloudy
)
val testCoordinate1 = Coordinate(-1.29987, 36.7724)

val mainDto = MainDto(
    temp = 17.18,
    tempMax = 17.82,
    tempMin = 14.38
)

val weatherDto = WeatherDto(
    id = 800,
    main = "Clear"
)
val testCurrentWeatherDto = CurrentWeatherDto(
    mainDto = mainDto,
    weatherDto = listOf(
        weatherDto
    )
)

val testFiveDayForecastDto = FiveDayForecastDto(
    dayWeatherDtoList = listOf(
        DayWeatherDto(
            dt = 1693677600,
            dtTxt = "2023-09-02 18:00:00",
            mainDto = mainDto,
            weatherDto = listOf(weatherDto)
        )
    )
)


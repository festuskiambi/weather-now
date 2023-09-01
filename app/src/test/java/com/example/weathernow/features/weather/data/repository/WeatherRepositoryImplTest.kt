package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.currentWeatherDto
import com.example.weathernow.features.weather.data.remote.WeatherApi
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeatherRepositoryImplTest {
    private val api: WeatherApi = mockk()

    private val repository = WeatherRepositoryImpl(
        api = api
    )

    @BeforeEach
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `get all weather data successFully`() = runTest {
        coEvery { api.getCurrentWeather() } returns currentWeatherDto
        coEvery { api.getFiveDayForeCast ()}
    }
}
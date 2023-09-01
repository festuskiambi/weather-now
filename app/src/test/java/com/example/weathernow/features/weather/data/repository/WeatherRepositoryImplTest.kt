package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.example.weathernow.testCurrentWeatherDto
import com.example.weathernow.testFiveDayForecastDto
import com.example.weathernow.util.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.BeforeEach


class WeatherRepositoryImplTest {
    private val api: WeatherApi = mockk()

    private val repository = WeatherRepositoryImpl(
        api = api
    )

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `get all weather data successFully`() = runBlocking {
        coEvery { api.getCurrentWeather() } returns testCurrentWeatherDto
        coEvery { api.getFiveDayForeCast() } returns testFiveDayForecastDto

        val result = when (val res = repository.getWeather()) {
            is Result.Success -> res.data
            else -> null
        }

        assertEquals(result?.currentWeather?.currTemp, testCurrentWeatherDto.mainDto?.temp)
        coVerify { api.getCurrentWeather() }
        coVerify { api.getFiveDayForeCast() }
    }
}
package com.example.weathernow.features.weather.data.repository

import com.example.weathernow.features.weather.data.remote.WeatherApi
import com.example.weathernow.testCoordinate1
import com.example.weathernow.testCurrentWeatherDto
import com.example.weathernow.testFiveDayForecastDto
import com.example.weathernow.util.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


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
        coEvery {
            api.getCurrentWeather(
                testCoordinate1.latitude.toString(),
                testCoordinate1.longitude.toString()
            )
        } returns testCurrentWeatherDto
        coEvery {
            api.getFiveDayForeCast(
                testCoordinate1.latitude.toString(),
                testCoordinate1.longitude.toString()
            )
        } returns testFiveDayForecastDto

        val result = when (val res = repository.getWeather(testCoordinate1)) {
            is Result.Success -> res.data
            else -> null
        }

        assertEquals(result?.currentWeather?.currTemp, testCurrentWeatherDto.mainDto?.temp)
        assertEquals(
            result?.fiveDayForecast?.firstOrNull()?.currTemp,
            testFiveDayForecastDto.dayWeatherDtoList?.firstOrNull()?.mainDto?.temp
        )
        coVerify {
            api.getCurrentWeather(
                testCoordinate1.latitude.toString(),
                testCoordinate1.longitude.toString()
            )
        }
        coVerify {
            api.getFiveDayForeCast(
                testCoordinate1.latitude.toString(),
                testCoordinate1.longitude.toString()
            )
        }
    }
}
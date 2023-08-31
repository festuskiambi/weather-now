package com.example.weathernow.features.weather.presentation

import CoroutineRule
import app.cash.turbine.test
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.testWeather
import com.example.weathernow.util.UiText
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WeatherViewModelTest {
    lateinit var viewModel: WeatherViewModel

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Before
    fun setUp() {
        viewModel = WeatherViewModel(

        )
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `on first start up`() = runTest {
        viewModel.apply {
            assertEquals(WeatherUiState(isLoading = true), uiState.value)
        }
    }

    @Test
    fun `on location permission granted`() = runTest {
        viewModel.locationPermissionResult(true)

        viewModel.uiState.test {

            with(awaitItem()) {
                assertEquals(WeatherUiState(isLoading = true), this)
            }

            with(awaitItem()) {
                assertEquals(
                    WeatherUiState(
                        isLoading = false,
                        currentWeather = testWeather,
                        userMessage = null,
                        fiveDayForecast = listOf(testWeather)
                    ),
                    this,
                )
            }
        }

    }

    @Test
    fun `on location permission not granted`() = runTest {
        viewModel.locationPermissionResult(false)
        viewModel.uiState.test {

            with(awaitItem()) {
                assertEquals(WeatherUiState(isLoading = true), this)
            }

            with(awaitItem()) {
                assertEquals(
                    WeatherUiState(
                        isLoading = false,
                        currentWeather = Weather(),
                        userMessage = UiText.DynamicString(
                            "Weathernow requires location permission in order to serve you best, please grant them in the settings."
                        ),
                        fiveDayForecast = emptyList()
                    ),
                    this,
                )
            }
        }
    }
}
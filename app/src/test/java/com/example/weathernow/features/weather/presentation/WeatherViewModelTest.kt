package com.example.weathernow.features.weather.presentation

import CoroutineRule
import app.cash.turbine.test
import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.repository.WeatherRepository
import com.example.weathernow.features.weather.domain.useCase.LocationUseCase
import com.example.weathernow.features.weather.domain.useCase.WeatherUseCase
import com.example.weathernow.features.weather.fake.FakeLocationRepository
import com.example.weathernow.features.weather.fake.FakeWeatherRepository
import com.example.weathernow.testWeather
import com.example.weathernow.util.UserMessage
import io.mockk.clearAllMocks
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class WeatherViewModelTest {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var locationRepository: FakeLocationRepository
    private lateinit var locationUseCase: LocationUseCase
    private lateinit var weatherRepository: WeatherRepository
    private lateinit var weatherUseCase: WeatherUseCase

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Before
    fun setUp() {
        locationRepository = FakeLocationRepository()
        locationUseCase = LocationUseCase(locationRepository)
        weatherRepository = FakeWeatherRepository()
        weatherUseCase = WeatherUseCase(weatherRepository)

        viewModel = WeatherViewModel(
            locationUseCase = locationUseCase,
            weatherUseCase = weatherUseCase
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
                        userMessage = null,
                        allWeather = AllWeather(
                            currentWeather = testWeather,
                            fiveDayForecast = listOf(testWeather)
                        )
                    ),
                    this,
                )
            }
            cancelAndIgnoreRemainingEvents()

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
                        userMessage = UserMessage.LocationPermissionRequired,
                        allWeather = AllWeather()
                    ),
                    this,
                )
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}
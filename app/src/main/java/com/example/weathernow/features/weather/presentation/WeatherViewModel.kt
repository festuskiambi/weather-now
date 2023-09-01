package com.example.weathernow.features.weather.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.model.Coordinate
import com.example.weathernow.features.weather.domain.useCase.LocationUseCase
import com.example.weathernow.features.weather.domain.useCase.WeatherUseCase
import com.example.weathernow.util.Async
import com.example.weathernow.util.Result
import com.example.weathernow.util.UserMessage
import com.example.weathernow.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {
    private val userMessageChannel = Channel<UserMessage?>()
    val messages = userMessageChannel.receiveAsFlow()

    private val isLoading = MutableStateFlow(false)
    private val _weatherAsync: MutableStateFlow<Async<AllWeather>> = MutableStateFlow(Async.Loading)

    val uiState: StateFlow<WeatherUiState> = combine(
        isLoading,
        messages,
        _weatherAsync,
    ) { isLoading, messages, weatherAsync ->
        when (weatherAsync) {
            is Async.Loading -> WeatherUiState(isLoading = true)
            is Async.Success -> {
                WeatherUiState(
                    isLoading = isLoading,
                    userMessage = messages,
                    allWeather = weatherAsync.data
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = WeatherUiState(isLoading = true),
    )

    fun locationPermissionResult(isGranted: Boolean) {
        if (isGranted) {
            getCurrentLocation()
        } else {
            viewModelScope.launch {
                if (_weatherAsync.value is Async.Loading) {
                    _weatherAsync.value = Async.Success(AllWeather())
                }
                userMessageChannel.send(
                    UserMessage.LocationPermissionRequired
                )
            }
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch {
            val coordinate = handleGetLocationResult(locationUseCase.invoke())
            Log.d("viewmodel", coordinate.toString())
            coordinate?.let {
                val allWeather = handleWeatherResult(weatherUseCase.invoke(it))

                allWeather?.let { allWeather ->
                    _weatherAsync.value = Async.Success(allWeather)
                    userMessageChannel.send(null)
                }
            }
        }
    }

    private suspend fun handleWeatherResult(result: Result<AllWeather>): AllWeather? {
        return when (result) {
            is Result.Success -> {
                result.data
            }

            is Result.Error -> {
                isLoading.value = false
                when (result.exception) {
                    is UnknownHostException -> {
                        userMessageChannel.send(
                            UserMessage.UnknownHostException
                        )
                    }

                    else -> {
                        userMessageChannel.send(
                            UserMessage.SomethingWentWrong
                        )
                    }
                }
                null
            }
        }
    }

    private suspend fun handleGetLocationResult(result: Result<Coordinate>): Coordinate? {
        return when (result) {
            is Result.Success -> {
                result.data
            }

            is Result.Error -> {
                if (_weatherAsync.value is Async.Loading) {
                    isLoading.value = false
                    _weatherAsync.value = Async.Success(AllWeather())
                }
                userMessageChannel.send(
                    UserMessage.LocationPermissionRequired
                )
                null
            }
        }

    }

}
package com.example.weathernow.features.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.util.Async
import com.example.weathernow.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(

) : ViewModel() {

    private val isLoading = MutableStateFlow(false)
    private val _weatherAsync: MutableStateFlow<Async<Weather>> = MutableStateFlow(Async.Loading)

    val uiState: StateFlow<WeatherUiState> = combine(
        isLoading,
        _weatherAsync,

        ) { isLoading, weatherAsync ->
        when (weatherAsync) {
            is Async.Loading -> WeatherUiState(isLoading = true)
            is Async.Success -> {
                WeatherUiState(
                    isLoading = isLoading,
                    currentWeather = weatherAsync.data,
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = WeatherUiState(isLoading = true),
    )


}
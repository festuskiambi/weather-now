package com.example.weathernow.features.weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathernow.R
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.util.Async
import com.example.weathernow.util.UiText
import com.example.weathernow.util.WhileUiSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(

) : ViewModel() {

    private val userMessageChannel = Channel<UiText>()
    val messages = userMessageChannel.receiveAsFlow()

    private val isLoading = MutableStateFlow(false)
    private val _weatherAsync: MutableStateFlow<Async<Weather>> = MutableStateFlow(Async.Loading)

    val uiState: StateFlow<WeatherUiState> = combine(
        isLoading,
        messages,
        _weatherAsync,
        ) { isLoading, messages,weatherAsync ->
        when (weatherAsync) {
            is Async.Loading -> WeatherUiState(isLoading = true)
            is Async.Success -> {
                WeatherUiState(
                    isLoading = isLoading,
                    userMessage = messages,
                    currentWeather = weatherAsync.data,

                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = WeatherUiState(isLoading = true),
    )

    fun locationPermissionResult(isGranted: Boolean) {
       if (isGranted){
           getCurrentLocation()
       } else {
           viewModelScope.launch {

               if (_weatherAsync.value is Async.Loading) {
                   isLoading.value = false
                   _weatherAsync.value = Async.Success(Weather())
               }
               userMessageChannel.send(
                   UiText.DynamicString(
                       "Weathernow requires location permission in order to serve you best, please grant them in the settings."
                   )
               )

           }
       }
    }

    fun getCurrentLocation() {

    }

}
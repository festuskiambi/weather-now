package com.example.weathernow.features.weather.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.weathernow.R
import com.example.weathernow.features.weather.domain.model.AllWeather
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.features.weather.domain.model.WeatherType
import com.example.weathernow.features.weather.presentation.component.CurrentDayForecastItem
import com.example.weathernow.features.weather.presentation.component.DailyForecastItem
import com.example.weathernow.presentation.components.CircularIndeterminateProgressBar
import com.example.weathernow.presentation.theme.WeatherNowTheme
import com.example.weathernow.presentation.theme.superscriptTitle

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = LocalView.current.findViewTreeViewModelStoreOwner()
        .let { hiltViewModel(it!!) },
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },

        ) { paddingValues ->
        WeatherScreenContent(
            modifier = modifier.padding(paddingValues),
            uiState = uiState
        )

        uiState.userMessage?.let { userMessage ->
            val snackbarText = userMessage.message?.asString()

            LaunchedEffect(snackbarHostState) {
                if (snackbarText != null) {
                    snackbarHostState.showSnackbar(
                        message = snackbarText
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherScreenContent(
    modifier: Modifier = Modifier,
    uiState: WeatherUiState,
) {
    val currentWeather = uiState.allWeather?.currentWeather
    val backgroundColor = currentWeather?.weatherType?.backgroundColor ?: R.color.white
    val fiveDayForecast = uiState.allWeather?.fiveDayForecast
    Box(modifier = modifier) {
        if (uiState.isLoading == false) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = backgroundColor))
            ) {
                CurrentWeatherContent(
                    currentWeather = currentWeather
                )
                ForecastWeatherContent(
                    modifier = Modifier.weight(1f),
                    fiveDayForecast = fiveDayForecast,
                    currentWeather = currentWeather
                )
            }
        }
        CircularIndeterminateProgressBar(uiState.isLoading == true)
    }
}

@Composable
private fun CurrentWeatherContent(
    modifier: Modifier = Modifier,
    currentWeather: Weather?,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(340.dp)
    ) {
        currentWeather?.let { weather ->
            weather.weatherType?.let { weatherType ->
                Image(
                    painter = painterResource(weatherType.backgroundRes),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.FillBounds
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.White,
                        text = buildAnnotatedString {
                            append(weather.currTemp.toString())
                            withStyle(superscriptTitle) {
                                append("°")
                            }
                        },
                    )
                    Text(
                        text = weatherType.weatherDesc.uppercase(),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
private fun ForecastWeatherContent(
    modifier: Modifier = Modifier,
    fiveDayForecast: List<Weather>?,
    currentWeather: Weather?,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            CurrentDayForecast(currentWeather = currentWeather)
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Divider()
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        fiveDayForecast?.let {
            items(it) { weather ->
                DailyForecastItem(weather = weather)
            }
        }
    }

}

@Composable
private fun CurrentDayForecast(
    modifier: Modifier = Modifier,
    currentWeather: Weather?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CurrentDayForecastItem(
            temp = currentWeather?.minTemp, "min", Alignment.Start
        )

        CurrentDayForecastItem(currentWeather?.minTemp, "current", Alignment.CenterHorizontally)

        CurrentDayForecastItem(currentWeather?.minTemp, "max", Alignment.End)
    }
}

@Preview(name = "phone sunny", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun SunnyPreview() {
    Surface {
        WeatherNowTheme {
            WeatherScreenContent(
                uiState = WeatherUiState(
                    isLoading = false,
                    allWeather = AllWeather(
                        currentWeather = Weather(
                            currTemp = 24,
                            date = "29-08-2023",
                            minTemp = 16,
                            maxTemp = 27,
                            weatherType = WeatherType.Clear
                        ),
                        fiveDayForecast = listOf(
                            Weather(
                                currTemp = 24,
                                date = "29-08-2023",
                                minTemp = 16,
                                maxTemp = 27,
                                weatherType = WeatherType.Clear
                            )
                        )
                    )
                )
            )
        }
    }
}

@Preview(name = "phone cloudy", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun CloudyPreview() {
    Surface {
        WeatherNowTheme {
            WeatherScreenContent(
                uiState = WeatherUiState(
                    isLoading = false,
                    allWeather = AllWeather(
                        currentWeather = Weather(
                            currTemp = 24,
                            date = "29-08-2023",
                            minTemp = 16,
                            maxTemp = 27,
                            weatherType = WeatherType.Cloudy
                        ),
                        fiveDayForecast = listOf(
                            Weather(
                                currTemp = 24,
                                date = "29-08-2023",
                                minTemp = 16,
                                maxTemp = 27,
                                weatherType = WeatherType.Cloudy
                            )
                        )
                    )
                )
            )
        }
    }
}

@Preview(name = "phone rainy", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun RainPreview() {
    Surface {
        WeatherNowTheme {
            WeatherScreenContent(
                uiState = WeatherUiState(
                    isLoading = false,
                    allWeather = AllWeather(
                        currentWeather = Weather(
                            currTemp = 24,
                            date = "29-08-2023",
                            minTemp = 16,
                            maxTemp = 27,
                            weatherType = WeatherType.Rain
                        ),
                        fiveDayForecast = listOf(
                            Weather(
                                currTemp = 24,
                                date = "29-08-2023",
                                minTemp = 16,
                                maxTemp = 27,
                                weatherType = WeatherType.Rain
                            )
                        )
                    )
                )
            )
        }
    }
}
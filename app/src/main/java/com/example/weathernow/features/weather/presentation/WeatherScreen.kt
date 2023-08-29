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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weathernow.R
import com.example.weathernow.features.weather.presentation.component.DailyForecastItem
import com.example.weathernow.presentation.theme.WeatherNowTheme
import com.example.weathernow.presentation.theme.superscriptBody
import com.example.weathernow.presentation.theme.superscriptTitle

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,

    ) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },

        ) { paddingValues ->
        WeatherScreenContent(
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
fun WeatherScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF54717A))
    ) {
        CurrentWeatherContent()
        ForecastWeatherContent(
            modifier = Modifier.weight(1f)
        )
    }

}

@Composable
private fun CurrentWeatherContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.forest_cloudy),
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
                    append("18")
                    withStyle(superscriptTitle) {
                        append("°")
                    }
                },
            )
            Text(
                text = "CLOUDY",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun ForecastWeatherContent(modifier: Modifier) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = buildAnnotatedString {
                        append("18")
                        withStyle(superscriptBody) {
                            append("°")
                        }
                    },
                )
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = "min"
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = buildAnnotatedString {
                        append("18")
                        withStyle(superscriptBody) {
                            append("°")
                        }
                    },
                )
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = "current"
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = buildAnnotatedString {
                        append("18")
                        withStyle(superscriptBody) {
                            append("°")
                        }
                    },
                )
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    text = "max"
                )
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            item {
                DailyForecastItem()
            }
            item {
                DailyForecastItem()
            }
            item {
                DailyForecastItem()
            }
            item {
                DailyForecastItem()
            }
            item {
                DailyForecastItem()
            }
        }

    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Composable
fun WeatherScreenContentPreview() {
    Surface {
        WeatherNowTheme {
            WeatherScreenContent()
        }
    }
}
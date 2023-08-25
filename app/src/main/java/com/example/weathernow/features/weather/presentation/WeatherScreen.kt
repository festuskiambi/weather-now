package com.example.weathernow.features.weather.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weathernow.presentation.theme.WeatherNowTheme

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,

    ) {
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
    Box {

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
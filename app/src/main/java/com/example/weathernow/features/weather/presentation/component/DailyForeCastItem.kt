package com.example.weathernow.features.weather.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.weathernow.features.weather.domain.model.Weather
import com.example.weathernow.presentation.theme.superscriptBody

@Composable
fun DailyForecastItem(
    modifier: Modifier = Modifier,
    weather: Weather,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                text = "Tuesday",
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            weather.weatherType?.let {
                Image(
                    painter = painterResource(id = it.iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End

        ) {

            Text(
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                text = buildAnnotatedString {
                    append(weather.maxTemp.toString())
                    withStyle(superscriptBody) {
                        append("°")
                    }
                },

                )
        }
    }
}
package com.example.weathernow.features.weather.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.weathernow.presentation.theme.superscriptBody


@Composable
fun CurrentDayForecastItem(
    temp: Double?,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            text = buildAnnotatedString {
                append(temp.toString())
                withStyle(superscriptBody) {
                    append("°")
                }
            },
        )
        Text(
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            text = label
        )
    }
}
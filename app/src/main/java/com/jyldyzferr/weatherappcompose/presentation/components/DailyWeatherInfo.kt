package com.jyldyzferr.weatherappcompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherDayInfoUi

@Composable
fun DailyWeatherInfo(
    weather: WeatherDayInfoUi,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
        InfoColumn(
            day = weather.day,
            text = weather.temperature.toString()
        )
    }
}

@Composable
fun InfoColumn(
    day: String,
    text: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = day,
            maxLines = 1,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

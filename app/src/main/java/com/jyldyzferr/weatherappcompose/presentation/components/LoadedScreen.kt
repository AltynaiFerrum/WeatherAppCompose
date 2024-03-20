package com.jyldyzferr.weatherappcompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyldyzferr.weatherappcompose.R
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherDayInfoUi
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherHourInfoUi
import com.jyldyzferr.weatherappcompose.presentation.utils.WeatherType
import com.jyldyzferr.weatherappcompose.presentation.utils.fetchWeatherType

@Composable
fun LoadedWeatherScreen(
    currentWeather: WeatherDayInfoUi,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        val backgroundImageId = when (fetchWeatherType()) {
            WeatherType.SUNNY -> R.drawable.background_weather
            WeatherType.RAINY -> R.drawable.rainy_background
            else -> {}
        }
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.rainy_background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Row(
                modifier = modifier
                    .padding(top = 18.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Icon(
                    Icons.Default.LocationOn,
                    tint = Color.White,
                    contentDescription = null,
                )
                Spacer(modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.city_name),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    tint = Color.White,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.avatar_weather),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = currentWeather.weatherConditionType.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                modifier = Modifier
                    .size(160.dp),
                painter = painterResource(
                    id = currentWeather.weatherConditionType.lightImageID,
                ),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "${currentWeather.temperature}",
                fontSize = 20.sp,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            Row {
                Text(
                    text = "${currentWeather.temperature}",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${currentWeather.temperature}",
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            DailyWeatherInfo(
                weather = currentWeather
            )
            Spacer(modifier = Modifier.height(10.dp))
            HourlyWeatherChart(
                dailyWeather = currentWeather
            )
        }
    }
}

package com.jyldyzferr.weatherappcompose.presentation.models

import com.jyldyzferr.weatherappcompose.data.models.TemperatureUnit
import java.util.Date
import kotlin.math.roundToInt

data class WeatherHourInfoUi(
    val temperature: Double,
    val weatherConditionType: WeatherConditionType,
    val date: Date,
    val windSpeed: Double,
){
    companion object {
        val unknown = WeatherHourInfoUi(
            temperature = 0.0,
            weatherConditionType = WeatherConditionType.UNKNOWN,
            windSpeed = 0.0,
            date = Date(),
        )
    }
}

data class WeatherDayInfoUi(
    val temperature: Double,
    val weatherConditionType: WeatherConditionType,
    val time: String,
    val day: String,
    val windSpeed: Double,
    val hourlyWeathers: List<WeatherHourInfoUi>
) {
    companion object {
        val unknown = WeatherDayInfoUi(
            temperature = 0.0,
            weatherConditionType = WeatherConditionType.UNKNOWN,
            windSpeed = 0.0,
            time = "unknown",
            hourlyWeathers = emptyList(),
            day = "Sunday"
        )
    }
}

fun Double.displayName(temperatureUnit: TemperatureUnit) =
    if (temperatureUnit == TemperatureUnit.Centigrade) this.toString()
    else (this * 1.8f + 32).roundToInt().toString()

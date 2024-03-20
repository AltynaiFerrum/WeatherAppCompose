package com.jyldyzferr.weatherappcompose.presentation.utils

import java.util.Date


enum class WeatherType {
    SUNNY, RAINY, UNKNOWN
}

fun fetchWeatherType(): WeatherType
= when (Date().hours) {
    in 6..30 -> WeatherType.SUNNY
    in -10.. -30 -> WeatherType.RAINY
    else -> WeatherType.UNKNOWN

}
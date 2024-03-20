package com.jyldyzferr.weatherappcompose.presentation.models

import androidx.annotation.DrawableRes
import com.jyldyzferr.weatherappcompose.R

enum class WeatherConditionType(
    val description: String,
    val weatherCodes: List<Int>,
    @DrawableRes val lightImageID: Int,
    @DrawableRes val background: Int,
) {

    CLEAR_SKY(
        description = "Clear sky",
        weatherCodes = listOf(0),
        lightImageID = R.drawable.sunny_clear_light,
        background = R.drawable.background_weather
    ),
    MAINLY_CLEAR(
        description = "Mainly clear",
        weatherCodes = listOf(1),
        lightImageID = R.drawable.sunny_clear_light,
        background = R.drawable.background_weather

    ),
    PARTLY_CLOUDY(
        description = "Partly cloudy",
        weatherCodes = listOf(2),
        lightImageID = R.drawable.partly_cloudy_light,
        background = R.drawable.background_weather

    ),
    OVERCAST(
        description = "Overcast",
        weatherCodes = listOf(3),
        lightImageID = R.drawable.partly_cloudy_light,
        background = R.drawable.background_weather
    ),
    FOG(
        description = "Fog",
        weatherCodes = listOf(45),
        lightImageID = R.drawable.partly_cloudy_light,
        background = R.drawable.background_weather
    ),
    DEPOSITING_FOG(
        description = "Depositing fog",
        weatherCodes = listOf(48),
        lightImageID = R.drawable.partly_cloudy_light,
        background = R.drawable.background_weather
    ),
    RAINY_SMALL(
        description = "Light rain",
        weatherCodes = listOf(51,56, 61),
        lightImageID = R.drawable.rain_small_light,
        background = R.drawable.rainy_background
    ),
    RAINY_MEDIUM(
        description = "Moderate rain",
        weatherCodes = listOf(53,63),
        lightImageID = R.drawable.mostly_rainy,
        background = R.drawable.rainy_background
    ),
    RAINY_HIGHT(
        description = "Intensity rain",
        weatherCodes = listOf(55,57,65),
        lightImageID = R.drawable.mostly_rainy,
        background = R.drawable.rainy_background
    ),
    FREEZING_RAIN_SMALL(
        description = "Light freezing rain",
        weatherCodes = listOf(66),
        lightImageID = R.drawable.mostly_rainy,
        background = R.drawable.rainy_background
    ),
    FREEZING_RAIN_MEDIUM(
        description = "Moderate freezing rain",
        weatherCodes = listOf(67),
        lightImageID = R.drawable.mostly_rainy,
        background = R.drawable.rainy_background
    ),
    STORM_MEDIUM(
        description = "Moderate storm",
        weatherCodes = listOf(95, 99),
        lightImageID = R.drawable.storm_medium_light,
        background = R.drawable.rainy_background
    ),
    UNKNOWN(
        description = "Unknown",
        weatherCodes = listOf(-1),
        lightImageID = R.drawable.sunny_clear_light,
        background = R.drawable.background_weather
    );

    companion object {
        fun findWeatherTypeByCode(weatherCode:Int): WeatherConditionType =
            values().find { weatherConditionType ->
                weatherConditionType.weatherCodes.contains(weatherCode)
            }?: UNKNOWN
    }
}
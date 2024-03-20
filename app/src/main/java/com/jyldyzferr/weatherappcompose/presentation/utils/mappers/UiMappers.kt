package com.jyldyzferr.weatherappcompose.presentation.utils.mappers

import com.jyldyzferr.weatherappcompose.domain.models.WeatherDayInfoDomain
import com.jyldyzferr.weatherappcompose.domain.models.WeatherDomain
import com.jyldyzferr.weatherappcompose.domain.models.WeatherHourInfoDomain
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherConditionType
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherDayInfoUi
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherHourInfoUi
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherUi


fun WeatherHourInfoDomain.toUi(): WeatherHourInfoUi {
    return this.run {
        WeatherHourInfoUi(
            temperature = temperature,
            weatherConditionType = WeatherConditionType.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            date = date
        )
    }
}

fun WeatherDayInfoDomain.toUi(): WeatherDayInfoUi {
    return if (this == WeatherDayInfoDomain.unknown)
        WeatherDayInfoUi.unknown
    else this.run {
        WeatherDayInfoUi(
            temperature = temperature,
            weatherConditionType = WeatherConditionType.findWeatherTypeByCode(weatherCode),
            windSpeed = windSpeed,
            time = time,
            day = day,
            hourlyWeathers = hourlyWeathers.map { it.toUi() }
        )
    }
}

fun WeatherDomain.toUi(): WeatherUi {
    return if (this.isUnknown()) WeatherUi.unknown
    else WeatherUi(
        currentWeather = currentWeather.toUi(),
        otherWeathers= otherWeathers.map{it.toUi()}
    )
}
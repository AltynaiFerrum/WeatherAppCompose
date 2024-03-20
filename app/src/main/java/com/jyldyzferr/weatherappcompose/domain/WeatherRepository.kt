package com.jyldyzferr.weatherappcompose.domain

import com.jyldyzferr.weatherappcompose.domain.models.WeatherDomain


interface WeatherRepository {

    suspend fun fetchWeatherFor16Days(
        longitude: String,
        latitude: String
    ): WeatherDomain
}
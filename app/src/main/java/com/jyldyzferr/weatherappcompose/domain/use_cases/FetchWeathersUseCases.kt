package com.jyldyzferr.weatherappcompose.domain.use_cases

import com.jyldyzferr.weatherappcompose.domain.models.WeatherDomain
import com.jyldyzferr.weatherappcompose.presentation.models.CountryInfo


interface FetchWeathersUseCases {

    suspend operator fun invoke(): Pair<WeatherDomain, CountryInfo>
}
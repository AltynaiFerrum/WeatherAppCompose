package com.jyldyzferr.weatherappcompose.di

import com.jyldyzferr.weatherappcompose.data.remote.WeatherService
import com.jyldyzferr.weatherappcompose.data.repository.WeatherRepositoryImpl
import com.jyldyzferr.weatherappcompose.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideWeatherRepository(
    weatherService: WeatherService
    ): WeatherRepository = WeatherRepositoryImpl(
        weatherService
    )
}
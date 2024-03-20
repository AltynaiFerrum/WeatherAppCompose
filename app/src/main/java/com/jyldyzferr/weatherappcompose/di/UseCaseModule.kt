package com.jyldyzferr.weatherappcompose.di

import android.content.Context
import com.jyldyzferr.weatherappcompose.domain.WeatherRepository
import com.jyldyzferr.weatherappcompose.domain.managers.LocationTrackerManager
import com.jyldyzferr.weatherappcompose.domain.use_cases.FetchWeathersUseCases
import com.jyldyzferr.weatherappcompose.domain.use_cases.FetchWeathersUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchWeathersUseCases(
        @ApplicationContext context: Context,
        repository: WeatherRepository,
        locationTrackerManager: LocationTrackerManager
    ): FetchWeathersUseCases = FetchWeathersUseCasesImpl(
        repository = repository,
        locationTrackerManager = locationTrackerManager,
        context = context
    )
}
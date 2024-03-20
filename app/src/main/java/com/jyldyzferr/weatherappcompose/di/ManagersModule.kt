package com.jyldyzferr.weatherappcompose.di

import android.app.Application
import com.google.android.gms.location.LocationServices
import com.jyldyzferr.weatherappcompose.domain.managers.LocationImpl
import com.jyldyzferr.weatherappcompose.domain.managers.LocationTrackerManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagersModule {

    @Provides
    fun provideLocationTrackerManager(
        application: Application
    ): LocationTrackerManager = LocationImpl(
        application = application,
        locationClient = LocationServices.getFusedLocationProviderClient(application)
    )
}
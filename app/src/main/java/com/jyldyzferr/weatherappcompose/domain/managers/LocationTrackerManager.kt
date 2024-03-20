package com.jyldyzferr.weatherappcompose.domain.managers

import android.location.Location

interface LocationTrackerManager {

    suspend fun fetchCurrentLocation(): Location?
}
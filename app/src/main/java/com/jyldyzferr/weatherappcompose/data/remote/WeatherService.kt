package com.jyldyzferr.weatherappcompose.data.remote

import com.jyldyzferr.weatherappcompose.data.models.WeatherRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast/daily?lat={lat}&lon={lon}&cnt={cnt}")
    suspend fun fetchWeatherFor16Day(
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String
    ): Response<WeatherRemote>
}
package com.jyldyzferr.weatherappcompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jyldyzferr.weatherappcompose.presentation.components.ErrorWeatherScreen
import com.jyldyzferr.weatherappcompose.presentation.components.LoadedWeatherScreen
import com.jyldyzferr.weatherappcompose.presentation.components.LoadingWeatherScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WeatherScreen(
    uiStateFlow: StateFlow<MainScreenUiState>,
    modifier: Modifier = Modifier,
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    val fullScreenModifier = Modifier
        .fillMaxSize()
        .background(Color.Transparent)

    when (uiState) {
        is MainScreenUiState.Loading -> {
            LoadingWeatherScreen(fullScreenModifier)
        }

        is MainScreenUiState.Loaded -> {
            LoadedWeatherScreen(
                currentWeather = uiState.weather.currentWeather
            )
        }
        is MainScreenUiState.Error -> {
            ErrorWeatherScreen(uiState.message, fullScreenModifier)
        }
    }
}





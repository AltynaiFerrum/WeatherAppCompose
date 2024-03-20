package com.jyldyzferr.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jyldyzferr.weatherappcompose.presentation.screens.WeatherScreen
import com.jyldyzferr.weatherappcompose.presentation.screens.WeatherScreenViewModel
import com.jyldyzferr.weatherappcompose.ui.theme.WeatherAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: WeatherScreenViewModel = hiltViewModel()
                    WeatherScreen(
                        uiStateFlow = viewModel.uiState
                    )
                }
            }
        }
    }
}


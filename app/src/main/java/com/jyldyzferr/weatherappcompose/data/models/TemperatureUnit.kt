package com.jyldyzferr.weatherappcompose.data.models

import androidx.compose.runtime.compositionLocalOf
import kotlin.math.roundToInt

enum class TemperatureUnit(val text: String) {
    Fahrenheit("℉"), Centigrade("℃")
}

val LocalTemUnit = compositionLocalOf<TemperatureUnit> { error("No data found!") }

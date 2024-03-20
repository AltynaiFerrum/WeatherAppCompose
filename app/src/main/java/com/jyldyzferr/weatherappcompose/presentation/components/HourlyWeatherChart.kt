package com.jyldyzferr.weatherappcompose.presentation.components

import android.graphics.LinearGradient
import android.graphics.Path
import android.graphics.Shader
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyldyzferr.weatherappcompose.data.models.LocalTemUnit
import com.jyldyzferr.weatherappcompose.presentation.models.WeatherDayInfoUi
import com.jyldyzferr.weatherappcompose.presentation.models.displayName
import com.jyldyzferr.weatherappcompose.ui.theme.FontType
import kotlin.math.abs

@Composable
fun HourlyWeatherChart(
    modifier: Modifier = Modifier,
    dailyWeather: WeatherDayInfoUi
) {
    LazyRow(
        modifier
            .height(100.dp)
            .wrapContentWidth()
    ) {
        item {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(900.dp)
            ) {
                Row(Modifier.fillMaxSize()) {
                    dailyWeather.hourlyWeathers.forEachIndexed { index, it ->
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .alpha(0.6f)
                                .align(Alignment.Bottom)
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .scale(0.6f)
                            ) {
                                it.weatherConditionType.lightImageID
                            }
                            Text(
                                if (index < 12) "${(index + 1)} AM"
                                else "${(index - 11)} PM",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .clearAndSetSemantics { }
                            )
                        }
                    }
                }
                LineChart(Modifier.fillMaxSize(), dailyWeather)
            }
        }
    }
}


@Composable
fun LineChart(
    modifier: Modifier,
    dailyWeather: WeatherDayInfoUi
) {
    val (cur, setCur) = remember { mutableStateOf<WeatherDayInfoUi?>(null) }
    var trigger by remember { mutableStateOf(0f) }

    DisposableEffect(dailyWeather) {
        trigger = 1f
        onDispose { }
    }

    val animateFloat by animateFloatAsState(
        targetValue = trigger,
        animationSpec = tween(1500)
    ) {
        setCur(dailyWeather)
        trigger = 0f
    }

    val tempUnit = LocalTemUnit.current
    Canvas(modifier) {

        val increment = size.width / dailyWeather.hourlyWeathers.size
        val max = dailyWeather.hourlyWeathers.maxOf { it.temperature }
        val min = dailyWeather.hourlyWeathers.minOf { it.temperature }
        val dy = (max - min).toFloat()

        drawIntoCanvas { canvas ->

            if (cur != dailyWeather) {
                // change visible range according to animation
                canvas.clipRect(Rect(0f, 0f, size.width * animateFloat, size.height))
            }

            val path = Path()

            val points = dailyWeather.hourlyWeathers.mapIndexed { index, hourlyWeather ->
                Offset(
                    increment * index + increment / 2,
                    ((1 - (hourlyWeather.temperature - min) / dy) * (size.height * 0.3f) +
                            size.height * 0.2f).toFloat() // reserve space for drawText
                )
            }

            path.moveTo(0f, points.first().y)
            path.lineTo(points.first().x, points.first().y)

            (0..points.size - 2).forEach { index ->

                val startP = points[index]
                val endP = points[index + 1]

                val p2: Offset
                val p3: Offset
                val cx = (startP.x + endP.x) / 2
                val dy = abs((endP.y - startP.y) / 4)
                if (endP.y < startP.y) {
                    p2 = Offset(cx, startP.y - dy)
                    p3 = Offset(cx, endP.y + dy)
                } else {
                    p2 = Offset(cx, startP.y + dy)
                    p3 = Offset(cx, endP.y - dy)
                }
                path.cubicTo(p2.x, p2.y, p3.x, p3.y, endP.x, endP.y)
            }

            path.lineTo(points.last().x + increment / 2, points.last().y)
            path.lineTo(points.last().x + increment / 2, size.height)
            path.lineTo(0f, size.height)
            path.lineTo(0f, points.first().y)

            // draw path
            canvas.nativeCanvas.drawPath(
                path,
                android.graphics.Paint().apply {
                    val linearGradient = LinearGradient(
                        0f, 0f,
                        0f, 200f,
                        Color.Black.copy(alpha = 0.1f).toArgb(),
                        Color.Transparent.toArgb(),
                        Shader.TileMode.CLAMP
                    )

                    shader = linearGradient
                    style = android.graphics.Paint.Style.FILL
                    isAntiAlias = true
                }
            )

            // draw points
            canvas.drawPoints(
                PointMode.Points, points,
                Paint().apply {
                    strokeWidth = 8f
                    strokeCap = StrokeCap.Round
                    color = Color.Black.copy(0.6f)
                }
            )

            // draw text
            val size = 10.sp.toPx()
            val textPaint = android.graphics.Paint().apply {
                color = Color.Black.toArgb()
                textSize = size
                alpha = 90
                typeface = FontType.typeface
            }
            dailyWeather.hourlyWeathers.asSequence().zip(points.asSequence())
                .forEachIndexed { index, pair ->
                    val (weather, points) = pair
                    canvas.nativeCanvas.drawText(
                        weather.temperature.displayName(tempUnit),
                        points.x - size / 2,
                        points.y - size / 1.5f,
                        textPaint
                    )
                }
        }
    }
}




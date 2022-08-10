package dev.yjyoon.alreadyme.ui.feature.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun LoadingScreen() {
    Box(
        Modifier.fillMaxSize().padding(bottom = 50.dp),
        contentAlignment = Alignment.Center
    ) {
        SquareFillLoaderAnimation()
        Text(
            "Creating README.md...",
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.padding(top = 160.dp)
        )
    }
}

@Composable
fun SquareFillLoaderAnimation() {

    val infiniteTransition = rememberInfiniteTransition()

    var rotation by remember { mutableStateOf(0f) }
    var height by remember { mutableStateOf(0f) }

    LaunchedEffect(key1 = Unit, block = {
        while (true) {
            animate(
                0f,
                180f,
                animationSpec = tween(500, easing = LinearEasing),
                block = { value, _ -> rotation = value }
            )
            animate(
                80f, 0f,
                animationSpec = tween(1000, easing = LinearEasing),
                block = { value, _ -> height = value }
            )
        }
    })

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.wrapContentSize()) {
            val topLeft = Offset(this.center.x - 40f, this.center.y - 40f)
            rotate(degrees = rotation) {
                drawRect(
                    R.color.DarkGray, topLeft, size = Size(80f, 80f),
                    style = Stroke(width = 6f)
                )
            }
            drawRect(
                R.color.DarkGray, topLeft, size = Size(80f, height)
            )
        }
    }
}

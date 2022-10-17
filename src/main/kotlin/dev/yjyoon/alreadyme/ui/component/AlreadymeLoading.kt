package dev.yjyoon.alreadyme.ui.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp

@Composable
fun AlreadymeLoading(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
    size: Float = 80f
) {

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
                size, 0f,
                animationSpec = tween(1000, easing = LinearEasing),
                block = { value, _ -> height = value }
            )
        }
    })

    Canvas(modifier = modifier.then(Modifier.wrapContentSize().padding((size / 1.5).dp))) {
        val topLeft = Offset(this.center.x - (size / 2), this.center.y - (size / 2))
        rotate(degrees = rotation) {
            drawRect(
                color, topLeft, size = Size(size, size),
                style = Stroke(width = (size / 12))
            )
        }
        drawRect(
            color, topLeft, size = Size(size, height)
        )
    }
}
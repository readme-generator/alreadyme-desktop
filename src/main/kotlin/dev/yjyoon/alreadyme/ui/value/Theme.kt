package dev.yjyoon.alreadyme.ui.value

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val LightTheme = lightColors(
    primary = R.color.Blue,
    onPrimary = R.color.White,
    secondary = R.color.Green,
    onSecondary = R.color.White,
    surface = R.color.LightGray,
    onSurface = R.color.DarkGray,
    background = R.color.LightGray,
    onBackground = R.color.DarkGray,
    error = R.color.Red,
    onError = R.color.White
)

val DarkTheme = darkColors(
    primary = R.color.Blue,
    onPrimary = R.color.White,
    secondary = R.color.Green,
    onSecondary = R.color.White,
    surface = R.color.DarkGray,
    onSurface = R.color.White,
    background = R.color.DarkGray,
    onBackground = R.color.White,
    error = R.color.Red,
    onError = R.color.White
)

@Composable
fun AlreadymeTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (isDarkTheme) DarkTheme else LightTheme,
        typography = Typography,
        content = content
    )
}

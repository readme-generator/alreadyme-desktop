package dev.yjyoon.alreadyme.ui.value

import androidx.compose.ui.graphics.Color

object R {
    @Suppress("ClassName")
    object color {
        val White = Color(0xFFFFFFFF)
        val LightGray = Color(0xFFF6F8FA)
        val DarkGray = Color(0xFF24292F)
        val Blue = Color(0xFF0969DA)
        val Green = Color(0xFF2C974B)
        val Red = Color(0xFFA40E26)
    }

    @Suppress("ClassName")
    object string {
        const val APP_TITLE = "ALREADYME.md"
        const val APP_DESCRIPTION = "The easiest way to build a README.md for your git repository"

        const val URL_INPUT_PLACEHOLDER = "Input your git repository URL here"
        const val URL_SUBMIT = "Get README.md"
    }
}
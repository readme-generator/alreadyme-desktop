package dev.yjyoon.alreadyme

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.yjyoon.alreadyme.ui.value.AlreadymeTheme
import dev.yjyoon.alreadyme.ui.value.R

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    AlreadymeTheme {
        Column {
            Text(
                R.string.APP_TITLE,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
        }

    }
}

package dev.yjyoon.alreadyme.ui.feature.title

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yjyoon.alreadyme.ui.value.AlreadymeTheme
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun TitleScreen(
    onPostUrl: (String) -> Unit
) {
    var url: String by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = R.string.APP_TITLE, fontSize = 54.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(
            text = R.string.APP_DESCRIPTION,
            fontSize = 22.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.ExtraLight
        )
        Spacer(Modifier.height(36.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                modifier = Modifier.width(640.dp),
                placeholder = {
                    Text(text = R.string.URL_INPUT_PLACEHOLDER)
                },
                singleLine = true
            )
            Spacer(Modifier.width(12.dp))
            Button(
                onClick = { onPostUrl(url) },
                contentPadding = PaddingValues(18.dp)
            ) {
                Text(R.string.URL_SUBMIT)
            }
        }
    }
}

@Preview
@Composable
private fun TitleScreenPreview() {
    AlreadymeTheme {
        TitleScreen(
            onPostUrl = {}
        )
    }
}

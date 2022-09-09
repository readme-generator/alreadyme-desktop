package dev.yjyoon.alreadyme.ui.feature.title

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(color = MaterialTheme.colors.onSurface)
                .padding(36.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource("drawables/img_logo.png"),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp).padding(top = 12.dp)
                )
                Spacer(Modifier.width(32.dp))
                Column {
                    Text(
                        text = R.string.APP_TITLE,
                        fontWeight = FontWeight.Bold,
                        fontSize = 72.sp,
                        color = MaterialTheme.colors.surface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = R.string.APP_DESCRIPTION,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colors.surface
                    )
                }
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(36.dp),
            contentAlignment = Alignment.TopCenter
        ) {
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
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 22.dp)
                ) {
                    Text(R.string.URL_SUBMIT)
                }
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

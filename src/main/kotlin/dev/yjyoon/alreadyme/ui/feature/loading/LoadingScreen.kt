package dev.yjyoon.alreadyme.ui.feature.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yjyoon.alreadyme.ui.component.AlreadymeLoading
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun LoadingScreen() {
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
            AlreadymeLoading()
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(36.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = R.string.CREATING_README,
                color = MaterialTheme.colors.onSurface,
            )
        }
    }
}

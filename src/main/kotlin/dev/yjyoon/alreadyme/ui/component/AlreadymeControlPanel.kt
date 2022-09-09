package dev.yjyoon.alreadyme.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState

@Composable
fun AlreadymeControlPanel(
    windowState: WindowState,
    onClose: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.onSurface)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = {
                windowState.isMinimized = true
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.surface
            )
        ) {
            Text("_")
        }
        TextButton(
            onClick = {
                if (windowState.placement == WindowPlacement.Floating) {
                    windowState.placement = WindowPlacement.Maximized
                } else {
                    windowState.placement = WindowPlacement.Floating
                }
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.surface
            )
        ) {
            Text("ㅁ")
        }
        TextButton(
            onClick = onClose,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.surface
            )
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
        }
    }
}

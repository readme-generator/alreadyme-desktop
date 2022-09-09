package dev.yjyoon.alreadyme.ui.feature.failure

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yjyoon.alreadyme.data.exception.HttpException
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun FailureScreen(
    throwable: Throwable,
    onBackToTitle: () -> Unit
) {
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
                    painter = painterResource("drawables/oops.png"),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp).padding(top = 12.dp)
                )
                Spacer(Modifier.width(32.dp))
                Column {
                    Text(text = R.string.OOPS, fontWeight = FontWeight.Bold, fontSize = 92.sp, color = R.color.White)
                    Text(text = R.string.ERROR_MESSAGE, fontSize = 28.sp, color = R.color.White)
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
            val statusCode = if (throwable is HttpException) {
                throwable.statusCode.toString()
            } else {
                R.string.UNKNOWN
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "$statusCode: ${throwable.message}")
                Text(text = "\n${R.string.TRY_AGAIN}")
                Spacer(Modifier.weight(1f))
                OutlinedButton(
                    onClick = onBackToTitle,
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp)
                ) {
                    Text(text = R.string.BACK_TO_HOME)
                }
            }
        }
    }
}

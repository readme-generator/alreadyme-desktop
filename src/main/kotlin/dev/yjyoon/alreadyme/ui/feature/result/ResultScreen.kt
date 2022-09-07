package dev.yjyoon.alreadyme.ui.feature.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
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
import com.mikepenz.markdown.Markdown
import com.mikepenz.markdown.MarkdownDefaults
import dev.yjyoon.alreadyme.ui.model.Readme
import dev.yjyoon.alreadyme.ui.value.MarkdownTypography
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun ResultScreen(
    readme: Readme,
    onDownload: (Long) -> Unit,
    onBackToTitle: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(color = MaterialTheme.colors.onSurface)
                .padding(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = R.string.README_CREATED,
                color = MaterialTheme.colors.surface,
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(Modifier.height(28.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 192.dp)
        ) {
            Markdown(
                content = readme.rawText,
                modifier = Modifier.padding(36.dp),
                typography = MarkdownDefaults.markdownTypography(
                    h1 = MarkdownTypography.h1,
                    h2 = MarkdownTypography.h2,
                    h3 = MarkdownTypography.h3,
                    h4 = MarkdownTypography.h4,
                    h5 = MarkdownTypography.h5,
                    h6 = MarkdownTypography.h6,
                    body1 = MarkdownTypography.body1,
                    body2 = MarkdownTypography.body2
                )
            )
        }
        Spacer(Modifier.height(28.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = MaterialTheme.colors.onSurface)
                .padding(36.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 192.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = onBackToTitle,
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp)
                ) {
                    Text(text = R.string.BACK_TO_HOME)
                }
                Button(
                    onClick = { onDownload(readme.id) },
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp)
                ) {
                    Text(text = R.string.DOWNLOAD_DIRECTLY)
                }
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = R.color.Green,
                        contentColor = R.color.White
                    ),
                    contentPadding = PaddingValues(vertical = 18.dp, horizontal = 28.dp)
                ) {
                    Icon(
                        painter = painterResource("drawables/ic_git.svg"),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSecondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(12.dp))
                    Text(text = R.string.PR_TO_REPOSITORY)
                }
            }
        }
    }
}

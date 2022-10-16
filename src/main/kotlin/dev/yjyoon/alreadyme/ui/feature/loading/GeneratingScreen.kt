package dev.yjyoon.alreadyme.ui.feature.loading

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.Markdown
import com.mikepenz.markdown.MarkdownDefaults
import dev.yjyoon.alreadyme.ui.component.AlreadymeLoading
import dev.yjyoon.alreadyme.ui.value.MarkdownTypography
import dev.yjyoon.alreadyme.ui.value.R

@Composable
fun GeneratingScreen(
    generatingReadme: String
) {
    Box(
        Modifier.fillMaxSize(),
    ) {
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = MaterialTheme.colors.onSurface
            ) { }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = MaterialTheme.colors.surface
            ) { }
        }
        Row(
            modifier = Modifier.fillMaxSize().align(Alignment.Center)
        ) {
            Card(
                modifier = Modifier
                    .weight(0.65f)
                    .padding(start = 48.dp, bottom = 48.dp)
                    .fillMaxHeight()
                    .width(720.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(width = 2.dp, color = R.color.DarkGray.copy(alpha = 0.12f))
            ) {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    Markdown(
                        content = generatingReadme,
                        modifier = Modifier.padding(24.dp),
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

                LaunchedEffect(generatingReadme) {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.35f)
                    .padding(bottom = 84.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AlreadymeLoading()
                Spacer(Modifier.height(54.dp))
                Text(
                    text = R.string.CREATING_README,
                    color = MaterialTheme.colors.onSurface,
                )
            }
        }
    }
}

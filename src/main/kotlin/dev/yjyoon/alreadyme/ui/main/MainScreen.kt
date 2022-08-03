package dev.yjyoon.alreadyme.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yjyoon.alreadyme.ui.feature.result.ResultScreen
import dev.yjyoon.alreadyme.ui.feature.title.TitleScreen

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        onPostUrl = { url: String -> viewModel.postUrl(scope, url) }
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    onPostUrl: (String) -> Unit
) {
    Column(
        Modifier.fillMaxSize().background(color = MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            MainUiState.Waiting -> {
                TitleScreen(onPostUrl = onPostUrl)
            }

            MainUiState.Generating -> {
                CircularProgressIndicator()
            }

            is MainUiState.Done -> {
                ResultScreen(readme = uiState.readme)
            }
        }
    }
}

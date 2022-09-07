package dev.yjyoon.alreadyme.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yjyoon.alreadyme.ui.feature.loading.LoadingScreen
import dev.yjyoon.alreadyme.ui.feature.result.ResultScreen
import dev.yjyoon.alreadyme.ui.feature.title.TitleScreen

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        onPostUrl = { url: String -> viewModel.postUrl(scope, url) },
        onDownload = { id: Long -> viewModel.downloadReadme(scope, id) },
        onBackToTitle = viewModel::backToTitle
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    onPostUrl: (String) -> Unit,
    onDownload: (Long) -> Unit,
    onBackToTitle: () -> Unit
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
                LoadingScreen()
            }

            is MainUiState.Done -> {
                ResultScreen(
                    readme = uiState.readme,
                    onDownload = onDownload,
                    onBackToTitle = onBackToTitle
                )
            }
        }
    }
}

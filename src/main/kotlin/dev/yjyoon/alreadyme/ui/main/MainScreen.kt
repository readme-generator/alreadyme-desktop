package dev.yjyoon.alreadyme.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import dev.yjyoon.alreadyme.ui.feature.failure.FailureScreen
import dev.yjyoon.alreadyme.ui.feature.loading.GeneratingScreen
import dev.yjyoon.alreadyme.ui.feature.loading.LoadingScreen
import dev.yjyoon.alreadyme.ui.feature.result.ResultScreen
import dev.yjyoon.alreadyme.ui.feature.title.TitleScreen

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    val uriHandler = LocalUriHandler.current

    MainScreen(
        uiState = uiState,
        generatingReadme = viewModel.generatingReadme,
        onPostUrl = { url: String -> viewModel.postUrl(scope, url) },
        onDownload = { id: Long -> viewModel.downloadReadme(scope, id) },
        onPullRequest = { id: Long -> viewModel.pullRequestReadme(scope, id) { uriHandler.openUri(it) } },
        onCloseDialog = viewModel::closeDialog,
        onBackToTitle = viewModel::backToTitle
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    generatingReadme: String,
    onPostUrl: (String) -> Unit,
    onDownload: (Long) -> Unit,
    onPullRequest: (Long) -> Unit,
    onCloseDialog: () -> Unit,
    onBackToTitle: () -> Unit
) {
    Crossfade(targetState = uiState) {
        when (it) {
            MainUiState.Waiting -> {
                TitleScreen(onPostUrl = onPostUrl)
            }

            MainUiState.Loading -> {
                LoadingScreen()
            }

            MainUiState.Generating -> {
                GeneratingScreen(generatingReadme)
            }

            is MainUiState.Success -> {
                ResultScreen(
                    readme = it.readme,
                    isLoading = it.isLoading,
                    actionDialog = it.actionDialog,
                    onCloseDialog = onCloseDialog,
                    onDownload = onDownload,
                    onPullRequest = onPullRequest,
                    onBackToTitle = onBackToTitle
                )
            }

            is MainUiState.Failure -> {
                FailureScreen(
                    throwable = it.throwable,
                    onBackToTitle = onBackToTitle
                )
            }
        }
    }
}

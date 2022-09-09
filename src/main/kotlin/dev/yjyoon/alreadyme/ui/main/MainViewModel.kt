package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.data.exception.toHttpException
import dev.yjyoon.alreadyme.data.model.toReadme
import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import dev.yjyoon.alreadyme.ui.value.R
import io.ktor.client.plugins.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val readmeRepository: ReadmeRepository
) {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Waiting)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    fun postUrl(scope: CoroutineScope, url: String) {
        scope.launch {
            _uiState.value = MainUiState.Generating
            readmeRepository.generateReadme(url)
                .onSuccess { _uiState.value = MainUiState.Success(readme = it.toReadme()) }
                .onFailure { onHttpRequestFailure(it) }
        }
    }

    fun downloadReadme(scope: CoroutineScope, id: Long) {
        _uiState.update { (it as MainUiState.Success).copy(isLoading = true) }

        scope.launch {
            readmeRepository.downloadReadme(id)
                .onSuccess {
                    _uiState.update {
                        (it as MainUiState.Success).copy(
                            isLoading = false,
                            actionDialog = MainUiState.Success.ActionDialog(
                                isVisible = true,
                                message = R.string.DOWNLOAD_COMPLETE
                            )
                        )
                    }
                }
                .onFailure { onHttpRequestFailure(it) }
        }
    }

    fun pullRequestReadme(scope: CoroutineScope, id: Long) {
        _uiState.update { (it as MainUiState.Success).copy(isLoading = true) }

        scope.launch {
            readmeRepository.pullRequestReadme(id)
                .onSuccess {
                    _uiState.update {
                        (it as MainUiState.Success).copy(
                            isLoading = false,
                            actionDialog = MainUiState.Success.ActionDialog(
                                isVisible = true,
                                message = R.string.PR_COMPLETE
                            )
                        )
                    }
                }
                .onFailure { onHttpRequestFailure(it) }

        }
    }

    fun closeDialog() {
        _uiState.update {
            (it as MainUiState.Success).copy(actionDialog = MainUiState.Success.ActionDialog.NONE)
        }
    }

    private fun onHttpRequestFailure(throwable: Throwable) {
        _uiState.value =
            MainUiState.Failure(
                if (throwable is HttpRequestTimeoutException) {
                    throwable.toHttpException()
                } else {
                    throwable
                }
            )
    }

    fun backToTitle() {
        _uiState.value = MainUiState.Waiting
    }
}

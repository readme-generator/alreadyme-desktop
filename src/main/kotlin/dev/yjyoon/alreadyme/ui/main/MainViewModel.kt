package dev.yjyoon.alreadyme.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.yjyoon.alreadyme.data.exception.toHttpException
import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import dev.yjyoon.alreadyme.data.util.FileSavingCanceledException
import dev.yjyoon.alreadyme.ui.model.Readme
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

    var generatingReadme by mutableStateOf("")
        private set

    fun postUrl(scope: CoroutineScope, url: String) {
        scope.launch {
            _uiState.value = MainUiState.Generating
            readmeRepository.postUrl(
                url = url,
                onReceive = { generatingReadme += it },
                onComplete = {
                    _uiState.value = MainUiState.Success(readme = Readme(id = 0, rawText = generatingReadme))
                }
            ).onFailure { onHttpRequestFailure(it) }
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
                .onFailure { throwable ->
                    when (throwable) {
                        is FileSavingCanceledException -> {
                            _uiState.update {
                                (it as MainUiState.Success).copy(
                                    isLoading = false,
                                )
                            }
                        }

                        else -> {
                            onHttpRequestFailure(throwable)
                        }
                    }
                }
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
        generatingReadme = ""
    }
}

package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.data.model.toReadme
import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                .onSuccess { _uiState.value = MainUiState.Done(readme = it.toReadme()) }
        }
    }

    fun downloadReadme(scope: CoroutineScope, id: Long) {
        scope.launch {
            readmeRepository.downloadReadme(id).getOrThrow()
        }
    }

    fun backToTitle() {
        _uiState.value = MainUiState.Waiting
    }
}

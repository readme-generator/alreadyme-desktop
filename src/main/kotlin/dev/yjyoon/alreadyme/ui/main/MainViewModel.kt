package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.data.repository.ReadmeRepository
import dev.yjyoon.alreadyme.ui.model.Readme
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
            readmeRepository.test(url)
                .onSuccess { _uiState.value = MainUiState.Done(readme = Readme("Test README.md")) }
        }
    }
}

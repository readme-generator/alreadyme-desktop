package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.ui.model.Readme

sealed interface MainUiState {
    object Waiting : MainUiState
    object Generating : MainUiState
    data class Success(val readme: Readme) : MainUiState
    data class Failure(val throwable: Throwable) : MainUiState
}

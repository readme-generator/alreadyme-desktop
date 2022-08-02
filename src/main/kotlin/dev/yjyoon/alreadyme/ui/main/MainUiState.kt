package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.ui.model.Readme

sealed interface MainUiState {
    object Waiting : MainUiState
    object Generating : MainUiState
    data class Done(val readme: Readme) : MainUiState
}

package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.ui.model.Readme

sealed interface MainUiState {
    object Waiting : MainUiState
    object Loading : MainUiState
    object Generating : MainUiState
    data class Success(
        val readme: Readme,
        val isLoading: Boolean = false,
        val actionDialog: ActionDialog = ActionDialog.NONE
    ) : MainUiState {

        data class ActionDialog(
            val isVisible: Boolean,
            val message: String?
        ) {

            companion object {
                val NONE = ActionDialog(isVisible = false, message = null)
            }
        }
    }

    data class Failure(val throwable: Throwable) : MainUiState
}

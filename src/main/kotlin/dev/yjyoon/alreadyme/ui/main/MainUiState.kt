package dev.yjyoon.alreadyme.ui.main

import dev.yjyoon.alreadyme.ui.component.AlreadymeDialogType
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
            val type: AlreadymeDialogType,
            val message: String?,
            val action: () -> Unit = {}
        ) {

            companion object {
                val NONE =
                    ActionDialog(isVisible = false, type = AlreadymeDialogType.Simple, message = null, action = {})
            }
        }
    }

    data class Failure(val throwable: Throwable) : MainUiState
}

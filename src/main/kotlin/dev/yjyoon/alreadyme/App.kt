package dev.yjyoon.alreadyme

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import dev.yjyoon.alreadyme.ui.di.DaggerViewModelComponent
import dev.yjyoon.alreadyme.ui.main.MainScreen
import dev.yjyoon.alreadyme.ui.main.MainViewModel
import dev.yjyoon.alreadyme.ui.value.AlreadymeTheme

@Composable
fun App(
    controlPanel: @Composable () -> Unit
) {
    val mainViewModel: MainViewModel = DaggerViewModelComponent.create().callMainViewModel()

    AlreadymeTheme {
        Column {
            controlPanel()
            MainScreen(mainViewModel)
        }
    }
}

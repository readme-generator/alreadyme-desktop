package dev.yjyoon.alreadyme

import androidx.compose.runtime.Composable
import dev.yjyoon.alreadyme.ui.di.DaggerViewModelComponent
import dev.yjyoon.alreadyme.ui.main.MainScreen
import dev.yjyoon.alreadyme.ui.main.MainViewModel
import dev.yjyoon.alreadyme.ui.value.AlreadymeTheme

@Composable
fun App() {
    val mainViewModel: MainViewModel = DaggerViewModelComponent.create().callMainViewModel()

    AlreadymeTheme(
        isDarkTheme = false
    ) {
        MainScreen(mainViewModel)
    }
}

package dev.yjyoon.alreadyme.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        test = { viewModel.test(scope) }
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    test: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (uiState) {
            MainUiState.Waiting -> {
                Button(onClick = test) {
                    Text("Get README.md")
                }
            }
            MainUiState.Generating -> {
                CircularProgressIndicator()
            }
            is MainUiState.Done -> {
                Text(uiState.readme.markdown)
            }
        }
    }
}

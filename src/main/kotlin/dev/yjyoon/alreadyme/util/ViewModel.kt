package dev.yjyoon.alreadyme.util

import kotlinx.coroutines.CoroutineScope

abstract class ViewModel {

    private lateinit var viewModelScope: CoroutineScope

    fun init(viewModelScope: CoroutineScope) {
        this.viewModelScope = viewModelScope
    }
}

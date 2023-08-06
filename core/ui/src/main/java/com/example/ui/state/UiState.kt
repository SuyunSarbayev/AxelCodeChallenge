package com.example.ui.state

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()

    class Error(val error: Throwable) : UiState<Nothing>()

    class Success<T>(val data: T) : UiState<T>()
}

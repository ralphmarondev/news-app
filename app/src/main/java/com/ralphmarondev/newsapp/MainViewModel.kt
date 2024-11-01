package com.ralphmarondev.newsapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme: StateFlow<Boolean> get() = _darkTheme

    fun toggleDarkTheme() {
        _darkTheme.value = !_darkTheme.value
    }
}
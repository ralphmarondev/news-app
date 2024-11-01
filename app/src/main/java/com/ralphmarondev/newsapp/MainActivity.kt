package com.ralphmarondev.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.ralphmarondev.newsapp.navigation.AppNavigation
import com.ralphmarondev.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContent {
            val darkTheme by viewModel.darkTheme.collectAsState()

            NewsAppTheme(darkTheme = darkTheme) {
                AppNavigation(
                    isDarkTheme = darkTheme,
                    toggleAppTheme = { viewModel.toggleDarkTheme() }
                )
            }
        }
    }
}
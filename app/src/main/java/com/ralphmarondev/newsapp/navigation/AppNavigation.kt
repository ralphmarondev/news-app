package com.ralphmarondev.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ralphmarondev.newsapp.features.details.presentation.DetailScreen
import com.ralphmarondev.newsapp.features.home.presentation.HomeScreen

@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    toggleAppTheme: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ) {
        composable<Routes.Home> {
            HomeScreen(
                isDarkTheme = isDarkTheme,
                toggleAppTheme = toggleAppTheme,
                navigateToDetailScreen = { url ->
                    navController.navigate(Routes.Details(url))
                }
            )
        }
        composable<Routes.Details> {
            val args = it.toRoute<Routes.Details>()
            DetailScreen(
                url = args.url
            )
        }
    }
}
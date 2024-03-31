package com.example.jetweatherforecast.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweatherforecast.ui.views.main.MainView
import com.example.jetweatherforecast.ui.views.search.SearchView
import com.example.jetweatherforecast.ui.views.splash.SplashView

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ViewEnum.SPLASH.name) {
        composable(ViewEnum.SPLASH.name) {
            SplashView(navController)
        }

        composable(ViewEnum.MAIN.name) {
            MainView(navController)
        }

        composable(ViewEnum.SEARCH.name) {
            SearchView(navController)
        }
    }
}
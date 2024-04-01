package com.example.jetweatherforecast.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetweatherforecast.ui.views.about.AboutView
import com.example.jetweatherforecast.ui.views.favorites.FavoritesView
import com.example.jetweatherforecast.ui.views.main.MAIN_VIEW_PARAM
import com.example.jetweatherforecast.ui.views.main.MainView
import com.example.jetweatherforecast.ui.views.search.SearchView
import com.example.jetweatherforecast.ui.views.settings.SettingsView
import com.example.jetweatherforecast.ui.views.splash.SplashView

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ViewEnum.SPLASH.name) {
        composable(ViewEnum.SPLASH.name) {
            SplashView(navController)
        }

        composable(
            ViewEnum.MAIN.name + "/{$MAIN_VIEW_PARAM}",
            arguments = listOf(navArgument(name = MAIN_VIEW_PARAM) {
                type = NavType.StringType
            })
        ) { navBack ->
            navBack.arguments?.getString(MAIN_VIEW_PARAM)?.let {
                MainView(navController, city = it)
            }
        }

        composable(ViewEnum.SEARCH.name) {
            SearchView(navController)
        }

        composable(ViewEnum.ABOUT.name) {
            AboutView(navController)
        }

        composable(ViewEnum.FAVORITES.name) {
            FavoritesView(navController)
        }

        composable(ViewEnum.SETTINGS.name) {
            SettingsView(navController)
        }
    }
}
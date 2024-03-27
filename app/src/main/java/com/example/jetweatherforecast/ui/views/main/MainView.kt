package com.example.jetweatherforecast.ui.views.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.util.showToast

@Composable
fun MainView(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    ShowForecast(mainViewModel)
}

@Composable
fun ShowForecast(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val forecast = mainViewModel.forecast.collectAsState().value
    val error = mainViewModel.error.collectAsState().value

    when {
        mainViewModel.loading.collectAsState().value -> CircularProgressIndicator()
        error != null -> context.showToast(error)
        forecast != null -> Text(text = "MAIN ${forecast.city.country}")
    }

}
package com.example.jetweatherforecast.ui.views.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.ui.widgets.AppBar
import com.example.jetweatherforecast.util.showToast

@Composable
fun MainView(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val context = LocalContext.current
    val forecast = mainViewModel.forecast.collectAsState().value
    val error = mainViewModel.error.collectAsState().value

    when {
        mainViewModel.loading.collectAsState().value -> CircularProgressIndicator()
        error != null -> context.showToast(error)
        forecast != null -> MainScaffold(navController, forecast)
    }
}

@Composable
fun MainScaffold(navController: NavController, forecast: Forecast) {
    Scaffold(topBar = {
        AppBar(
            title = forecast.city.name + ", " + forecast.city.country,
            navController = navController,
            elevation = 4.dp,
            onNavigationIconClick = {
                /* TODO */
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "search")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "more")
                }
            }
        )
    }) {
        MainContent(forecast, it)
    }
}

@Composable
fun MainContent(forecast: Forecast, padding: PaddingValues) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = padding.calculateTopPadding(),
                start = 28.dp,
                end = 28.dp,
                bottom = 12.dp
            )
    ) {
        Text(text = forecast.city.name)
    }
}

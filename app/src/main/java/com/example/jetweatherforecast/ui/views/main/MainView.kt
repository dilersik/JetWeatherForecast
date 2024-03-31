package com.example.jetweatherforecast.ui.views.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.ui.widgets.AppBar
import com.example.jetweatherforecast.util.formatDate
import com.example.jetweatherforecast.util.formatDateTime
import com.example.jetweatherforecast.util.formatDecimals
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = padding
                    .calculateTopPadding()
                    .plus(10.dp),
                start = 28.dp,
                end = 28.dp,
                bottom = 12.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = forecast.getToday().formatDate(),
            modifier = Modifier.padding(6.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(forecast.getTodayIcon())
                Text(
                    text = forecast.getTodayTempDay().formatDecimals(),
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forecast.getTodayDescription(),
                    style = MaterialTheme.typography.titleMedium,
                    fontStyle = FontStyle.Italic
                )
            }
        }
        TodayDetailsRow(forecast)
        HorizontalDivider()
        SunsetSunRiseRow(forecast)
    }
}

@Composable
fun SunsetSunRiseRow(forecast: Forecast) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise_icon),
                contentDescription = "sunrise",
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = forecast.getTodaySunrise().formatDateTime(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.sunset_icon),
                contentDescription = "sunset",
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = forecast.getTodaySunset().formatDateTime(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun TodayDetailsRow(forecast: Forecast) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.humidity_icon), contentDescription = "",
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${forecast.getTodayHumidity()}%",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.pressure_icon), contentDescription = "",
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${forecast.getTodayPressure()} psi",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.wind_icon), contentDescription = "",
                modifier = Modifier.size(ICON_SIZE)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${forecast.getTodayWind().formatDecimals()} psi",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun WeatherStateImage(iconUrl: String) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(iconUrl)
            .crossfade(true)
            .transformations(RoundedCornersTransformation())
            .build()
    )
    Image(painter = painter, contentDescription = "", modifier = Modifier.size(60.dp))
}

private val ICON_SIZE = 30.dp
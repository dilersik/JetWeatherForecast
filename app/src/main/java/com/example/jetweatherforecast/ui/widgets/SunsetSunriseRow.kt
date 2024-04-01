package com.example.jetweatherforecast.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.util.Constant.ICON_SIZE
import com.example.jetweatherforecast.util.formatDateTime

@Composable
fun SunsetSunriseRow(forecast: Forecast) {
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
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Text(
                text = forecast.getTodaySunrise().formatDateTime(),
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.sunset_icon),
                contentDescription = "sunset",
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Text(
                text = forecast.getTodaySunset().formatDateTime(),
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
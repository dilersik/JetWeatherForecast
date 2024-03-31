package com.example.jetweatherforecast.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.jetweatherforecast.R
import com.example.jetweatherforecast.model.Daily
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.ui.theme.Yellow
import com.example.jetweatherforecast.util.Constant.ICON_SIZE
import com.example.jetweatherforecast.util.formatDateDay
import com.example.jetweatherforecast.util.formatDecimals

@Composable
fun DailyWeatherRow(daily: Daily) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp), bottomStart = CornerSize(6.dp)),
        color = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = daily.dt.formatDateDay(), modifier = Modifier.padding(start = 10.dp))

            WeatherStateImage(iconUrl = daily.getTodayIcon())

            Surface(shape = CircleShape, color = Yellow) {
                Text(
                    daily.weather.first().description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = Color.Red.copy(alpha = 0.7f),
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(daily.temp.max.formatDecimals() + "°")
                    }

                    withStyle(SpanStyle(color = Color.Blue.copy(alpha = 0.7f))) {
                        append(daily.temp.min.formatDecimals() + "°")
                    }
                },
                modifier = Modifier.padding(end = 10.dp)
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
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Text(
                text = "${forecast.getTodayHumidity()}%",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.pressure_icon), contentDescription = "",
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Text(
                text = "${forecast.getTodayPressure()} psi",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.wind_icon), contentDescription = "",
                modifier = Modifier.size(ICON_SIZE.dp)
            )
            Text(
                text = "${forecast.getTodayWind().formatDecimals()} km/h",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp),
            )
        }
    }
}
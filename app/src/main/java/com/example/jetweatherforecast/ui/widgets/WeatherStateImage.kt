package com.example.jetweatherforecast.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

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
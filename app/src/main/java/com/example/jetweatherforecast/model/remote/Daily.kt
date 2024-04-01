package com.example.jetweatherforecast.model.remote

import com.example.jetweatherforecast.util.Constant

data class Daily(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feelsLike: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
) {
    fun getTodayIcon() = Constant.BASE_URL_IMAGE + weather.firstOrNull()?.icon + ".png"
}
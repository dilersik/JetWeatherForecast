package com.example.jetweatherforecast.model

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Daily>,
    val message: Double
)
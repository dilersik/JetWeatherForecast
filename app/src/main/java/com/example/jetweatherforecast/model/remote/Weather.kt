package com.example.jetweatherforecast.model.remote

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
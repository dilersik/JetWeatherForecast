package com.example.jetweatherforecast.repository.remote

import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.remote.Forecast

interface WeatherRemoteRepository {
    suspend fun getForecast(city:String, unit: String): ResultWrapper<Forecast>
}
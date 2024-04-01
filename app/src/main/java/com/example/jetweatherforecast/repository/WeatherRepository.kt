package com.example.jetweatherforecast.repository

import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.model.remote.UnitEnum

interface WeatherRepository {
    suspend fun getForecast(city:String, unit: String = UnitEnum.METRIC.name): ResultWrapper<Forecast>
}
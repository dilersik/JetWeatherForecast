package com.example.jetweatherforecast.repository

import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.UnitEnum

interface WeatherRepository {
    suspend fun getForecast(city:String, unit: String = UnitEnum.METRIC.name): ResultWrapper<Forecast>
}
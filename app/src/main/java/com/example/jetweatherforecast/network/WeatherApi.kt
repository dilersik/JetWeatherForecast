package com.example.jetweatherforecast.network

import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("units") units: String,
        @Query("appid") appId: String = Constant.API_KEY
    ): Forecast
}
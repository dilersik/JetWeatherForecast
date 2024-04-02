package com.example.jetweatherforecast.repository.remote

import android.util.Log
import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRemoteRepositoryImp @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRemoteRepository {

    override suspend fun getForecast(city: String, unit: String): ResultWrapper<Forecast> = try {
        val forecast = weatherApi.getForecast(query = city, units = unit)
        ResultWrapper.Success(forecast)
    } catch (e: Exception) {
        Log.e(TAG, "getAll: ${e.localizedMessage}")
        ResultWrapper.Error(e)
    }

    private companion object {
        private const val TAG = "WeatherRepositoryImp"
    }
}
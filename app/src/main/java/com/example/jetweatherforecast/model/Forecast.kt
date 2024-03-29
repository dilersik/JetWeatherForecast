package com.example.jetweatherforecast.model

import com.example.jetweatherforecast.util.Constant.BASE_URL_IMAGE

data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Daily>,
    val message: Double
) {
    fun getTodayIcon() = BASE_URL_IMAGE + list.firstOrNull()?.weather?.firstOrNull()?.icon + ".png"
    fun getToday() = list.firstOrNull()?.dt ?: 0
    fun getTodayTempDay() = list.firstOrNull()?.temp?.day ?: 0.0
    fun getTodayDescription() = list.firstOrNull()?.weather?.firstOrNull()?.main ?: ""
    fun getTodayHumidity() = list.firstOrNull()?.humidity ?: 0
    fun getTodayPressure() = list.firstOrNull()?.pressure ?: 0
    fun getTodayWind() = list.firstOrNull()?.speed ?: 0.0
}
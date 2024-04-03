package com.example.jetweatherforecast.model.remote

enum class UnitEnum(val text: String, val temperature: String, val velocity: String) {
    IMPERIAL("Imperial (F)", "F", "mph"),
    METRIC("Metric (C)", "C", "km/k")
}
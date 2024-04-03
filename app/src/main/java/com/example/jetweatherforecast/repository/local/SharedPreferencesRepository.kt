package com.example.jetweatherforecast.repository.local

import com.example.jetweatherforecast.model.remote.UnitEnum

interface SharedPreferencesRepository {
    fun saveUnit(unit: UnitEnum)
    fun getUnit(): UnitEnum?
}
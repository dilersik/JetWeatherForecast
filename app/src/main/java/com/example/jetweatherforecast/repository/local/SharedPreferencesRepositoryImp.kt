package com.example.jetweatherforecast.repository.local

import android.content.SharedPreferences
import com.example.jetweatherforecast.model.remote.UnitEnum
import javax.inject.Inject

class SharedPreferencesRepositoryImp @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferencesRepository {

    override fun saveUnit(unit: UnitEnum) {
        val editor = sharedPreferences.edit()
        editor.putString("UNIT", unit.name)
        editor.apply()
    }

    override fun getUnit(): UnitEnum? {
        val unit = sharedPreferences.getString("UNIT", "")
        return if (unit?.isNotEmpty() == true) UnitEnum.valueOf(unit) else null
    }
}
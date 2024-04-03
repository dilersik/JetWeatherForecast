package com.example.jetweatherforecast.ui.views.settings

import androidx.lifecycle.ViewModel
import com.example.jetweatherforecast.model.remote.UnitEnum
import com.example.jetweatherforecast.repository.local.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    fun getSavedUnit() = sharedPreferencesRepository.getUnit() ?: UnitEnum.METRIC

    fun saveUnit(unitEnum: UnitEnum) {
        sharedPreferencesRepository.saveUnit(unitEnum)
    }

}
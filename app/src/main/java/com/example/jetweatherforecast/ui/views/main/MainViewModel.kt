package com.example.jetweatherforecast.ui.views.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.local.Favorite
import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.model.remote.UnitEnum
import com.example.jetweatherforecast.repository.local.FavoriteLocalRepository
import com.example.jetweatherforecast.repository.local.SharedPreferencesRepository
import com.example.jetweatherforecast.repository.remote.WeatherRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRemoteRepository: WeatherRemoteRepository,
    private val favoriteLocalRepository: FavoriteLocalRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
) : ViewModel() {

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _forecast: MutableStateFlow<Forecast?> = MutableStateFlow(null)
    val forecast = _forecast.asStateFlow()

    fun loadForecast(city: String) = viewModelScope.launch {
        if (city.isEmpty()) return@launch
        _loading.value = true
        var unit = sharedPreferencesRepository.getUnit()
        if (unit == null) {
            unit = UnitEnum.METRIC
            sharedPreferencesRepository.saveUnit(unit)
        }

        when (val result = weatherRemoteRepository.getForecast(city, unit.name)) {
            is ResultWrapper.Success -> _forecast.value = checkFavorite(result.data, unit)
            is ResultWrapper.Error -> _error.value = result.exception.message
        }
        _loading.value = false
    }

    private suspend fun checkFavorite(forecast: Forecast, unitEnum: UnitEnum): Forecast {
        val localFavorite = favoriteLocalRepository.getByName(
            Favorite(
                city = forecast.city.name,
                country = forecast.city.country
            )
        )
        forecast.isFavorite = localFavorite != null
        forecast.unitEnum = unitEnum
        return forecast
    }

    fun addDelFavorite(favorite: Favorite) = viewModelScope.launch {
        try {
            val localFavorite = favoriteLocalRepository.getByName(favorite)
            if (localFavorite == null) {
                favoriteLocalRepository.insert(favorite)
                _forecast.value = _forecast.value?.copy(isFavorite = true)
            } else {
                favoriteLocalRepository.delete(localFavorite)
                _forecast.value = _forecast.value?.copy(isFavorite = false)
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            _error.value = e.message
        }
    }

    private companion object {
        private const val TAG = "MainViewModel"
    }

}
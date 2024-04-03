package com.example.jetweatherforecast.ui.views.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.model.local.Favorite
import com.example.jetweatherforecast.model.remote.Forecast
import com.example.jetweatherforecast.repository.local.FavoriteLocalRepository
import com.example.jetweatherforecast.repository.remote.WeatherRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRemoteRepository: WeatherRemoteRepository,
    private val favoriteLocalRepository: FavoriteLocalRepository
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
        when (val result = weatherRemoteRepository.getForecast(city)) {
            is ResultWrapper.Success -> _forecast.value = result.data
            is ResultWrapper.Error -> _error.value = result.exception.message
        }
        _loading.value = false
    }

    fun addDelFavorite(favorite: Favorite) = viewModelScope.launch {
        try {
            val localFavorite = favoriteLocalRepository.getByName(favorite)
            if (localFavorite == null)
                favoriteLocalRepository.insert(favorite)
            else
                favoriteLocalRepository.delete(localFavorite)
        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            _error.value = e.message
        }
    }

    private companion object {
        private const val TAG = "MainViewModel"
    }

}
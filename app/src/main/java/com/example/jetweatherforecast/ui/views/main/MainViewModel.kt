package com.example.jetweatherforecast.ui.views.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.model.Forecast
import com.example.jetweatherforecast.model.ResultWrapper
import com.example.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _loading.asStateFlow()

    private val forecast: MutableState<Forecast?> = mutableStateOf(null)

    init {
        loadForecast()
    }

    private fun loadForecast(city: String = "Curitiba") = viewModelScope.launch {
        if (city.isEmpty()) return@launch
        _loading.value = true
        when (val result = weatherRepository.getForecast(city)) {
            is ResultWrapper.Success -> forecast.value = result.data
            is ResultWrapper.Error -> _error.value = result.exception.message
        }
        _loading.value = false
    }

}
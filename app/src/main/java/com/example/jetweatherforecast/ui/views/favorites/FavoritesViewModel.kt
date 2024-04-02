package com.example.jetweatherforecast.ui.views.favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweatherforecast.model.local.Favorite
import com.example.jetweatherforecast.repository.local.FavoriteLocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteLocalRepository: FavoriteLocalRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites = _favorites.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                favoriteLocalRepository.getAll().distinctUntilChanged().collect {
                    _favorites.value = it
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

    private companion object {
        private const val TAG = "FavoritesViewModel"
    }
}
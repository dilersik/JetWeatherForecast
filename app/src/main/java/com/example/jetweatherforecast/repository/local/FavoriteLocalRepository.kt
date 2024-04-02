package com.example.jetweatherforecast.repository.local

import com.example.jetweatherforecast.model.local.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalRepository {
    fun getAll(): Flow<List<Favorite>>
    suspend fun insert(favorite: Favorite)
    suspend fun delete(favorite: Favorite)
}
package com.example.jetweatherforecast.repository.local

import com.example.jetweatherforecast.data.FavoriteDao
import com.example.jetweatherforecast.model.local.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteLocalRepositoryImp @Inject constructor(private val favoriteDao: FavoriteDao) :
    FavoriteLocalRepository {

    override fun getAll(): Flow<List<Favorite>> = favoriteDao.getAll()

    override suspend fun insert(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    override suspend fun delete(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

}
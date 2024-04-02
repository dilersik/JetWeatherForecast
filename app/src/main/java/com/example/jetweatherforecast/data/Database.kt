package com.example.jetweatherforecast.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetweatherforecast.model.local.Favorite

@Database(
    version = 1,
    exportSchema = false,
    entities = [Favorite::class]
)
abstract class Database : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
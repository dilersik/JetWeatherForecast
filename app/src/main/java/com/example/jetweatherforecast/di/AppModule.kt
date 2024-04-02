package com.example.jetweatherforecast.di

import android.content.Context
import androidx.room.Room
import com.example.jetweatherforecast.data.Database
import com.example.jetweatherforecast.data.FavoriteDao
import com.example.jetweatherforecast.network.WeatherApi
import com.example.jetweatherforecast.repository.local.FavoriteLocalRepository
import com.example.jetweatherforecast.repository.local.FavoriteLocalRepositoryImp
import com.example.jetweatherforecast.repository.remote.WeatherRemoteRepository
import com.example.jetweatherforecast.repository.remote.WeatherRemoteRepositoryImp
import com.example.jetweatherforecast.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    @Provides
    @Singleton
    fun provideWeatherRemoteRepository(weatherApi: WeatherApi): WeatherRemoteRepository =
        WeatherRemoteRepositoryImp(weatherApi)

    @Provides
    @Singleton
    fun provideFavoriteLocalRepository(favoriteDao: FavoriteDao): FavoriteLocalRepository =
        FavoriteLocalRepositoryImp(favoriteDao)

    @Provides
    @Singleton
    fun provideFavoriteDao(database: Database): FavoriteDao = database.favoriteDao()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database = Room
        .databaseBuilder(
            context,
            Database::class.java,
            "jet_weather_forecast"
        )
        .fallbackToDestructiveMigration()
        .build()
}
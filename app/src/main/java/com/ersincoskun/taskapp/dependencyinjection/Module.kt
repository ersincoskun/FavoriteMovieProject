package com.ersincoskun.taskapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ersincoskun.taskapp.api.RetrofitAPI
import com.ersincoskun.taskapp.roomDb.MovieDatabase
import com.ersincoskun.taskapp.util.Util.BASE_URL
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
object Module {

    @Singleton
    @Provides
    fun injectRoomDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, MovieDatabase::class.java, "MoviesDb").build()

    @Singleton
    @Provides
    fun injectDao(database: MovieDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun injectRetrofit(): RetrofitAPI =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build().create(RetrofitAPI::class.java)


}
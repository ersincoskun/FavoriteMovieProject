package com.ersincoskun.taskapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ersincoskun.taskapp.R
import com.ersincoskun.taskapp.api.RetrofitAPI
import com.ersincoskun.taskapp.repo.MovieRepository
import com.ersincoskun.taskapp.repo.MovieRepositoryInterface
import com.ersincoskun.taskapp.roomDb.MovieDao
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

    @Singleton
    @Provides
    fun injectRepo(dao: MovieDao, api: RetrofitAPI) =
        MovieRepository(dao, api) as MovieRepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(com.ersincoskun.taskapp.R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
        )


}
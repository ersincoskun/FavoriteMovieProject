package com.ersincoskun.taskapp.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ersincoskun.taskapp.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovies(vararg movies: Movie)

    @Query("SELECT * FROM movies WHERE uuid = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("SELECT * FROM movies")
    fun getMoviesFromDB(): List<Movie>

}
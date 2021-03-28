package com.ersincoskun.taskapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ersincoskun.taskapp.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovies(vararg movies: Movie)

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("SELECT * FROM movies")
    suspend fun getMoviesFromDB(): List<Movie>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}
package com.ersincoskun.taskapp.roomDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ersincoskun.taskapp.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun insertMovies(vararg movies: Movie)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("SELECT * FROM movies")
    suspend fun getMoviesFromDB(): List<Movie>

    @Query("DELETE FROM movies")
    suspend fun deleteAllMovies()

}
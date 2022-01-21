package com.ersincoskun.taskapp.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ersincoskun.taskapp.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(vararg movies: Movie)

    @Insert
    suspend fun insertMovie(movie: Movie)

    @Query("SELECT * FROM MOVIES WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("SELECT * FROM MOVIES")
    suspend fun getMoviesFromDB(): List<Movie>?

    @Query("DELETE FROM MOVIES")
    suspend fun deleteAllMovies()

}
package com.ersincoskun.taskapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ersincoskun.taskapp.model.Movie

@Dao
interface MovieDao {

    @Insert
    suspend fun saveAllMovies(vararg movies: Movie)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

}
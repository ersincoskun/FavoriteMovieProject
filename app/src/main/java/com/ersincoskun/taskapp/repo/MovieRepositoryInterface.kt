package com.ersincoskun.taskapp.repo

import androidx.lifecycle.LiveData
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.util.Resource

interface MovieRepositoryInterface {

    suspend fun saveAllMovies(list: List<Movie>)

    suspend fun getMovie(id: Long): Movie

    fun observeMovies(): LiveData<List<Movie>>

    suspend fun getMovies(): Resource<Response>
}
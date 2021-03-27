package com.ersincoskun.taskapp.repo

import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.util.Resource

interface MovieRepositoryInterface {

    suspend fun saveAllMovies(list: List<Movie>)

    suspend fun getMovie(id: Long): Movie

    suspend fun getMoviesFromDB(): List<Movie>

    suspend fun getMoviesFromAPI(): Resource<Response>

    suspend fun deleteAllMovies()
}
package com.ersincoskun.taskapp.repo

import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Person
import com.ersincoskun.taskapp.model.Response

interface MovieRepositoryInterface {

    suspend fun saveAllMovies(list: List<Movie>)

    suspend fun getMovie(id: Long): Movie

    suspend fun getMoviesFromDB(): List<Movie>

    suspend fun getMoviesFromAPI(): Response?

    suspend fun getPerson(movieId:Long): Person?

    suspend fun deleteAllMovies()
}
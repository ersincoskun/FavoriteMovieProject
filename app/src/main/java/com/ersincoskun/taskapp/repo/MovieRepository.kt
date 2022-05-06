package com.ersincoskun.taskapp.repo

import com.ersincoskun.taskapp.api.RetrofitAPI
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Person
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.roomdb.MovieDao
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val dao: MovieDao,
    val retrofitAPI: RetrofitAPI,
) : MovieRepositoryInterface {

    override suspend fun saveAllMovies(list: List<Movie>) {
        dao.insertMovies(*list.toTypedArray())
    }

    override suspend fun getMovie(id: Long): Movie {
        return dao.getMovie(id)
    }

    override suspend fun getMoviesFromDB(): List<Movie> {
        return dao.getMoviesFromDB() ?: emptyList()
    }


    override suspend fun getMoviesFromAPI(): Response? {
        val emptyResponse = Response(null, null)
        try {
            val response = retrofitAPI.getMovies()
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()
                } else {
                    emptyResponse
                }
            } else {
                emptyResponse
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyResponse
        }
    }

    override suspend fun getPerson(movieId:Long): Person? {
        val emptyPerson = Person(null, null)
        try {
            val response = retrofitAPI.getPerson("3/movie/$movieId/credits?api_key=16ee8b7fb226f8c089aec47408dfc917")
            return if (response.isSuccessful) {
                if (response.body() != null) {
                    response.body()
                } else {
                    emptyPerson
                }
            } else {
                emptyPerson
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyPerson
        }
    }

    override suspend fun deleteAllMovies() {
        dao.deleteAllMovies()
    }

}
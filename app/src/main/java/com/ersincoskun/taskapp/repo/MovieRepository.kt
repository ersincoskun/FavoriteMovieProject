package com.ersincoskun.taskapp.repo

import androidx.lifecycle.LiveData
import com.ersincoskun.taskapp.api.RetrofitAPI
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.roomDb.MovieDao
import com.ersincoskun.taskapp.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val dao: MovieDao,
    val retrofitAPI: RetrofitAPI
) : MovieRepositoryInterface {
    override suspend fun saveAllMovies(list: List<Movie>) {
        dao.insertMovies(*list.toTypedArray())
    }

    override suspend fun getMovie(id: Long): Movie {
        return dao.getMovie(id)
    }

    override fun observeMovies(): LiveData<List<Movie>> {
        return dao.observeMovies()
    }

    override suspend fun getMovies(): Resource<Response> {
        return try {
            val response = retrofitAPI.getMovies()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Data Null", null)
            } else {
                Resource.error("Error", null)
            }

        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }
}
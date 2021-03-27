package com.ersincoskun.taskapp.api

import com.ersincoskun.taskapp.model.Response
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("3/movie/popular?api_key=16ee8b7fb226f8c089aec47408dfc917")
    suspend fun getMovies(): retrofit2.Response<Response>

}
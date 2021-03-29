package com.ersincoskun.taskapp.api

import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.util.Util.URL
import retrofit2.http.GET

interface RetrofitAPI {

    @GET(URL)
    suspend fun getMovies(): retrofit2.Response<Response>

}
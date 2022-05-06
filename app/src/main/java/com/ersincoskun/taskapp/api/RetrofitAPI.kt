package com.ersincoskun.taskapp.api

import com.ersincoskun.taskapp.model.Person
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.util.Util.POPULAR_MOVIES_URL
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {

    @GET(POPULAR_MOVIES_URL)
    suspend fun getMovies(): retrofit2.Response<Response>

    @GET
    suspend fun getPerson(@Url url: String): retrofit2.Response<Person>

}
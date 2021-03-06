package com.ersincoskun.taskapp.util

object Util {
    const val POPULAR_MOVIES_URL = "3/movie/popular?api_key=16ee8b7fb226f8c089aec47408dfc917"
    const val BASE_URL = "https://api.themoviedb.org"
    const val IMAGE_URL = "http://image.tmdb.org/t/p/w500"

    interface PersonClickAction {
        fun onItemClick(
            name: String?,
            popularity: Double?,
            character: String?,
            gender: Int?,
            imageUrl: String?
        )
    }
}
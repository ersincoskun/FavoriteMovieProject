package com.ersincoskun.taskapp.repo

import com.ersincoskun.taskapp.model.Movie

class FakeMovieRepository : MovieRepositoryInterface {

    private val movies = mutableListOf<Movie>()

    override suspend fun saveAllMovies(list: List<Movie>) {
        movies.addAll(list)
    }

    override suspend fun getMovie(id: Long): Movie {
        lateinit var movie: Movie
        movies.forEach {
            if (it.id == id) movie = it
        }
        return movie
    }

    override suspend fun getMoviesFromDB(): List<Movie> {
        return movies
    }

    override suspend fun getMoviesFromAPI(): Response {
        val results = movies.map {
            val genreList = it.genre.split(",").map {
                it.toLong()
            }.toList()
            Result(
                it.id,
                it.adult,
                it.language,
                it.title,
                it.overview,
                it.popularity,
                it.img,
                it.release,
                it.voteAverage,
                it.voteCount,
                genreList
            )
        }.toMutableList()
        return Response(1, results)
    }

    override suspend fun deleteAllMovies() {
        movies.clear()
    }

}
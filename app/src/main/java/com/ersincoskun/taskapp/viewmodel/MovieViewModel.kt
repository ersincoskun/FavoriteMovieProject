package com.ersincoskun.taskapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.repo.MovieRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val movieRepository: MovieRepositoryInterface
) : ViewModel() {

    private val _allMovies = MutableLiveData<List<Movie>>()
    val allMovies: LiveData<List<Movie>>
        get() = _allMovies


    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    private fun saveAllMovies(list: List<Movie>) = viewModelScope.launch {
        movieRepository.deleteAllMovies()
        movieRepository.saveAllMovies(list)
    }

    fun getMovie(id: Long) = viewModelScope.launch {
        _movieDetail.value = movieRepository.getMovie(id)
    }

    fun getMoviesFromDB() = viewModelScope.launch {
        _allMovies.value = movieRepository.getMoviesFromDB()
    }

    private fun deleteAllMovies() = viewModelScope.launch {
        movieRepository.deleteAllMovies()
    }

    fun getMoviesFromAPI() {
        viewModelScope.launch {
            val response = movieRepository.getMoviesFromAPI()
            response.results?.let {
                val newResponse = it.map { movie->
                    //i do this due to i can't save a list to db
                    var genreStringGenerator = movie.genre[0].toString()
                    for (i in 1 until movie.genre.size) {
                        genreStringGenerator = "$genreStringGenerator,${movie.genre[i]}"
                    }
                    Movie(
                        movie.id,
                        movie.adult,
                        movie.language,
                        movie.title,
                        movie.overview,
                        movie.popularity,
                        movie.img,
                        movie.release,
                        movie.voteAverage,
                        movie.voteCount,
                        genreStringGenerator
                    )
                }
                deleteAllMovies()
                saveAllMovies(newResponse)
                _allMovies.value = newResponse
            }
        }
    }

    fun genreConverter(genreList: String): String {
        val genreMap = mapOf<String, String>(
            "28" to "Action",
            "16" to "Animated",
            "99" to "Documentary",
            "18" to "Drama",
            "10751" to "Family",
            "14" to "Fantasy",
            "36" to "History",
            "35" to "Comedy",
            "10752" to "War",
            "80" to "Crime",
            "10402" to "Music",
            "9648" to "Mystery",
            "878" to "Sci-Fi",
            "10749" to "Romance",
            "27" to "Horror",
            "10770" to "Tv Movie",
            "53" to "Thriller",
            "37" to "Western",
            "12" to "Adventure"
        )
        val myGenreList = genreList.split(",")
        var newGenreString = genreMap[myGenreList[0]]
        for (i in 1 until myGenreList.size) {
            newGenreString = "$newGenreString , ${genreMap[myGenreList[i]]}"
        }
        return newGenreString!!
    }

}
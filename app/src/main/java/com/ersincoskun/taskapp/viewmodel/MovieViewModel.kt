package com.ersincoskun.taskapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.model.Response
import com.ersincoskun.taskapp.repo.MovieRepositoryInterface
import com.ersincoskun.taskapp.util.Resource
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

    fun saveAllMovies(list: List<Movie>) = viewModelScope.launch {
        movieRepository.deleteAllMovies()
        movieRepository.saveAllMovies(list)
    }

    fun getMovie(id: Long) = viewModelScope.launch {
        _movieDetail.value = movieRepository.getMovie(id)
    }

    fun getMoviesFromDB() = viewModelScope.launch {
        _allMovies.value = movieRepository.getMoviesFromDB()
    }

    fun getMoviesFromAPI() {
        viewModelScope.launch {
            val response = movieRepository.getMoviesFromAPI()
            var newResponse = response.data!!.results.map {
                var genreStringGenerator = ""
                it.genre.forEach { genreId ->
                    genreStringGenerator = "$genreStringGenerator,$genreId"
                }
                Movie(
                    0,
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
                    genreStringGenerator
                )
            }
            saveAllMovies(newResponse)
            _allMovies.value = newResponse
        }
    }

}
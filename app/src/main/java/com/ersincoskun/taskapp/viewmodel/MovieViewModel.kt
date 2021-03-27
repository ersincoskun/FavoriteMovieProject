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

    private val _allMoviesFromDB = MutableLiveData<List<Movie>>()
    val allMoviesFromDB: LiveData<List<Movie>>
        get() = _allMoviesFromDB

    private val _allMoviesFromAPI = MutableLiveData<Resource<Response>>()
    val allMoviesFromAPI: LiveData<Resource<Response>>
        get() = _allMoviesFromAPI

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetail: LiveData<Movie>
        get() = _movieDetail

    fun saveAllMovies(list: List<Movie>) = viewModelScope.launch {
        movieRepository.saveAllMovies(list)
    }

    fun getMovie(id: Long) = viewModelScope.launch {
        _movieDetail.value = movieRepository.getMovie(id)
    }

    fun getMoviesFromDB() = viewModelScope.launch {
        _allMoviesFromDB.value = movieRepository.getMoviesFromDB()
    }

    fun getMoviesFromAPI() {
        _allMoviesFromAPI.value = Resource.loading(null)
        viewModelScope.launch {
            val response = movieRepository.getMoviesFromAPI()
            _allMoviesFromAPI.value = response
        }
    }

}
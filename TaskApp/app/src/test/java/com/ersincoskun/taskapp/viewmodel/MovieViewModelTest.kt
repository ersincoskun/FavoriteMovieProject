package com.ersincoskun.taskapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ersincoskun.taskapp.MainCoroutineRule
import com.ersincoskun.taskapp.repo.FakeMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule=MainCoroutineRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel(FakeMovieRepository())
    }

    @Test
    fun genreConverterTest(){
        val genre=viewModel.genreConverter("12,28,16")
        assertThat(genre).isEqualTo("Adventure , Action , Animated")
    }



}
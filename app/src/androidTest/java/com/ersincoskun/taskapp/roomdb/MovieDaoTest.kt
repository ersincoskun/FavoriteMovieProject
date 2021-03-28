package com.ersincoskun.taskapp.roomdb

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.ersincoskun.taskapp.model.Movie
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@SmallTest
@ExperimentalCoroutinesApi
class MovieDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.movieDao()

    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMoviesTest() = runBlocking {
        val moviesList = listOf<Movie>(
            Movie(
                122,
                true,
                "en",
                "Title",
                "overview",
                12.2,
                "www.ww.w",
                "release",
                22.2,
                333,
                "sada"
            ), Movie(
                33,
                false,
                "tr",
                "Title",
                "overview",
                33.2,
                "www.ww.w",
                "release",
                11.2,
                2222,
                "sada"
            )
        )

        dao.insertMovies(*moviesList.toTypedArray())

        val getMovies = dao.getMoviesFromDB()
        moviesList.forEach {
            assertThat(getMovies).contains(it)
        }

    }

    @Test
    fun getSingleMovieByIdTest() = runBlocking {

        val movie = Movie(
            33,
            false,
            "tr",
            "Title",
            "overview",
            33.2,
            "www.ww.w",
            "release",
            11.2,
            2222,
            "sada"
        )
        dao.insertMovie(movie)
        val getMovie = dao.getMovie(movie.id)
        assertThat(getMovie).isEqualTo(movie)
    }

    @Test
    fun deleteAllMoviesTest() = runBlocking {

        val moviesList = listOf<Movie>(
            Movie(
                122,
                true,
                "en",
                "Title",
                "overview",
                12.2,
                "www.ww.w",
                "release",
                22.2,
                333,
                "sada"
            ), Movie(
                33,
                false,
                "tr",
                "Title",
                "overview",
                33.2,
                "www.ww.w",
                "release",
                11.2,
                2222,
                "sada"
            )
        )

        dao.insertMovies(*moviesList.toTypedArray())
        dao.deleteAllMovies()
        val getMovies = dao.getMoviesFromDB()
        moviesList.forEach {
            assertThat(getMovies).doesNotContain(it)
        }

    }

}
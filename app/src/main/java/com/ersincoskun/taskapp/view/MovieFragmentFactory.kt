package com.ersincoskun.taskapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.adapter.MovieRecyclerAdapter
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val movieRecyclerAdapter: MovieRecyclerAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            MovieDetailFragment::class.java.name -> MovieDetailFragment(glide)
            PersonDetailFragment::class.java.name -> PersonDetailFragment(glide)
            PopularMoviesFragment::class.java.name -> PopularMoviesFragment(movieRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }

    }

}
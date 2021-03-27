package com.ersincoskun.taskapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            MovieDetailFragment::class.java.name -> MovieDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }

    }

}
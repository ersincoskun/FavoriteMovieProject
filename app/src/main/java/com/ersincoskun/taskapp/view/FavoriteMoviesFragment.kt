package com.ersincoskun.taskapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ersincoskun.taskapp.adapter.MovieRecyclerAdapter
import com.ersincoskun.taskapp.databinding.FragmentFavoriteMoviesBinding
import com.ersincoskun.taskapp.viewmodel.MovieViewModel
import javax.inject.Inject

class FavoriteMoviesFragment @Inject constructor(
    private val adapter: MovieRecyclerAdapter
) : Fragment() {

    private var _binding: FragmentFavoriteMoviesBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onStart() {
        super.onStart()
        binding.favoriteMoviesRecyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        observeLiveDataFromAPI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun observeLiveDataFromAPI() {
        viewModel.getMoviesFromAPI()
        viewModel.allMovies.observe(viewLifecycleOwner, Observer {
            adapter.movies = it
        })
    }

    fun observeLiveDataFromDB() {
        viewModel.getMoviesFromDB()
        viewModel.allMovies.observe(viewLifecycleOwner, Observer {
            adapter.movies = it
        })
    }

}
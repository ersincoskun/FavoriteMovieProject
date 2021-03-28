package com.ersincoskun.taskapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.databinding.FragmentMovieDetailBinding
import com.ersincoskun.taskapp.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieDetailFragment @Inject constructor(
    glide: RequestManager
) : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    val args: MovieDetailFragmentArgs by navArgs()
    lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        observeData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun observeData() {
        viewModel.getMovie(args.id)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            binding.titleTv.text = it.title
            binding.adultTv.text = if (it.adult) "Adults Only" else "Children and Adults"
            binding.languageTv.text = "Original Language : ${it.language}"
            binding.genreTv.text = viewModel.genreConverter(it.genre)
            binding.voteCountTv.text="Vote Count : ${it.voteCount}"
            binding.voteAverageTv.text="Vote Count : ${it.voteAverage}"
            binding.releaseTv.text="Release Date : ${it.release}"
            binding.popularityTv.text="Popularity : ${it.popularity}"
            binding.overviewTv.text=it.overview
        })
    }

}
package com.ersincoskun.taskapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.adapter.PersonListAdapter
import com.ersincoskun.taskapp.databinding.FragmentMovieDetailBinding
import com.ersincoskun.taskapp.util.Util
import com.ersincoskun.taskapp.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieDetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(), Util.PersonClickAction {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailFragmentArgs by navArgs()
    lateinit var viewModel: MovieViewModel
    private lateinit var adapter: PersonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        adapter = PersonListAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.personListRV.adapter = adapter
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        viewModel.getMovie(args.id)
        viewModel.movieDetail.observe(viewLifecycleOwner, Observer {
            binding.apply {
                titleTv.text = it.title
                adultTv.text = if (it.adult) "Adults Only" else "Children and Adults"
                languageTv.text = "Original Language : ${it.language}"
                genreTv.text = it.genre
                voteCountTv.text = "Vote Count : ${it.voteCount}"
                voteAverageTv.text = "Vote Count : ${it.voteAverage}"
                releaseTv.text = "Release Date : ${it.release}"
                popularityTv.text = "Popularity : ${it.popularity}"
                overviewTv.text = it.overview
                val imageUrl = "${Util.IMAGE_URL}${it.img}"
                glide.load(imageUrl).into(movieDetailImage)
            }
        })
        viewModel.getPersonList(args.id)
        viewModel.personList.observe(viewLifecycleOwner) {
            adapter.casts = it
        }
    }

    override fun onItemClick(
        name: String?,
        popularity: Double?,
        character: String?,
        gender: Int?,
        imageUrl: String?
    ) {
        val direction =
            MovieDetailFragmentDirections.actionMovieDetailFragmentToPersonDetailFragment(
                name ?: "",
                imageUrl ?: "",
                character ?: "",
                (popularity ?: 0).toFloat(),
                gender ?: 0
            )
        Navigation.findNavController(binding.personListRV).navigate(direction)
    }

}
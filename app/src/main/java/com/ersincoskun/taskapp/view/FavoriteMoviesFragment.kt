package com.ersincoskun.taskapp.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.ersincoskun.taskapp.R
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
    ): View {
        _binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews(){
        binding.apply {
            moviesProgressBar.visibility=View.VISIBLE
            favoriteMoviesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            favoriteMoviesRecyclerView.adapter = adapter
        }
        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeLiveData() {
        viewModel.getMoviesFromAPI()
        viewModel.allMovies.observe(viewLifecycleOwner, Observer {
            adapter.allRvItems.clear()
            binding.moviesProgressBar.visibility=View.GONE
            adapter.movies = it
            adapter.allRvItems.addAll(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.menu,menu)
        val item=menu.findItem(R.id.action_search)
        val searchView=item?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return false
            }

        })
    }

}
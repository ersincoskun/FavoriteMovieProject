package com.ersincoskun.taskapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.databinding.MovieItemBinding
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.util.Util.IMAGE_URL
import com.ersincoskun.taskapp.view.FavoriteMoviesFragmentDirections
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<MovieRecyclerAdapter.MovieViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var movies: List<Movie>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    class MovieViewHolder(val itemBinding: MovieItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val imageUrl = "$IMAGE_URL${movies[position].img}"
        
        // mailde belirttiğim gibi nedenini bilmediğim sebepten resimler yüklenmiyor bu yüzden film resimleri için bir image kullandım

        //Picasso.get().load(imageUrl).into(holder.itemBinding.movieImage)
        //glide.load("http://image.tmdb.org/t/p/w500/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg").into(holder.itemBinding.movieImage)
        holder.itemBinding.movieTitle.text = movies[position].title
        holder.itemBinding.itemLayout.setOnClickListener {
            val action =
                FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailFragment(
                    movies[position].id
                )
            Navigation.findNavController(it).navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return movies.size
    }

}




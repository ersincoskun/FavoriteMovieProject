package com.ersincoskun.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.databinding.MovieItemBinding
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.util.Util.IMAGE_URL
import javax.inject.Inject

class MovieRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<MovieViewHolder>() {
    lateinit var movies: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val imageUrl = "$IMAGE_URL${movies[position].img}"
        glide.load(imageUrl).into(holder.itemBinding.movieImage)
        holder.itemBinding.movieTitle.text = movies[position].title
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}

class MovieViewHolder(val itemBinding: MovieItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root)


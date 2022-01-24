package com.ersincoskun.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.databinding.MovieItemBinding
import com.ersincoskun.taskapp.model.Movie
import com.ersincoskun.taskapp.util.Util.IMAGE_URL
import com.ersincoskun.taskapp.view.PopularMoviesFragmentDirections
import javax.inject.Inject

class MovieRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<MovieViewHolder>(), Filterable {

    val allRvItems= mutableListOf<Movie>()

    val diffUtil = object : DiffUtil.ItemCallback<Movie>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding, glide)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getFilter(): Filter {
        return myFilter
    }

    private val myFilter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Movie>()
            if (p0.toString().isEmpty()) {
                filteredList.addAll(allRvItems)
            } else {
                allRvItems.forEach {
                    if (it.title.lowercase().contains(p0.toString().lowercase())) {
                        filteredList.add(it)
                    }
                }
            }
            val filterResult=FilterResults()
            filterResult.values=filteredList
            return filterResult
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            movies= p1?.values as List<Movie>
            notifyDataSetChanged()
        }

    }

}

class MovieViewHolder(private val itemBinding: MovieItemBinding, val glide: RequestManager) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(movie: Movie) {
        val imageUrl = "$IMAGE_URL${movie.img}"
        glide.load(imageUrl)
            .into(itemBinding.movieImage)
        itemBinding.movieTitle.text = movie.title
        itemBinding.itemLayout.setOnClickListener {
            val action =
                PopularMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailFragment(
                    movie.id
                )
            Navigation.findNavController(it).navigate(action)
        }
    }
}




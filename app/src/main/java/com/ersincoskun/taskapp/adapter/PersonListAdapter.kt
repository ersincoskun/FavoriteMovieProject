package com.ersincoskun.taskapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ersincoskun.taskapp.databinding.PersonItemBinding
import com.ersincoskun.taskapp.model.Cast
import com.ersincoskun.taskapp.util.Util

class PersonListAdapter(val personClickAction: Util.PersonClickAction) :
    RecyclerView.Adapter<PersonListAdapter.PersonListViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var casts: List<Cast>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListViewHolder {
        val itemBinding =
            PersonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PersonListViewHolder, position: Int) {
        holder.bind(casts[position])
    }

    override fun getItemCount(): Int {
        return casts.size
    }


    inner class PersonListViewHolder(private val itemBinding: PersonItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(cast: Cast) {
            itemBinding.personNameTV.text = cast.name
            itemBinding.goPersonDetailBtn.setOnClickListener {
                personClickAction.onItemClick(
                    cast.name,
                    cast.popularity,
                    cast.character,
                    cast.gender,
                    cast.imgUrl
                )
            }
        }
    }

}
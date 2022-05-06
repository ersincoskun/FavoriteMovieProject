package com.ersincoskun.taskapp.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.ersincoskun.taskapp.databinding.FragmentPersonDetailBinding
import com.ersincoskun.taskapp.util.Util
import javax.inject.Inject

class PersonDetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {

    private val binding by lazy {
        FragmentPersonDetailBinding.inflate(layoutInflater)
    }
    private val args: PersonDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        binding.apply {
            val imageUrl = "${Util.IMAGE_URL}${args.imageUrl}"
            glide.load(imageUrl)
                .into(personDetailImage)
            personDetailCharacterTV.text = "Character: ${args.character}"
            if (args.gender == 0)
                personDetailGenderTV.text = "Unknown"
            else
                personDetailGenderTV.text = "Gender: ${if (args.gender == 2) "Male" else "Female"}"

            personDetailNameTV.text = "Name: ${args.name}"
            personDetailPopularityTV.text = "Popularity: ${args.popularity}"
        }
    }

}
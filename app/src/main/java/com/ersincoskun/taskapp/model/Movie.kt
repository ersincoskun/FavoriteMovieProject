package com.ersincoskun.taskapp.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    val adult: Boolean,
    val id: Long,
    @SerializedName("original_language")
    val language: String,
    @SerializedName("original_title")
    val title: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val img: String,
    @SerializedName("release_date")
    val release: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long,
    @SerializedName("genre_ids")
    val genreId: List<Long>
)
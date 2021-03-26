package com.ersincoskun.taskapp.model

import com.google.gson.annotations.SerializedName

data class Result(
    val adult: Boolean,
    @SerializedName("original_language")
    val language: String,
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
    val genre: List<Long>
)
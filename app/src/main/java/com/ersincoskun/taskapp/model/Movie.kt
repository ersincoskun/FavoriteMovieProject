package com.ersincoskun.taskapp.model

import androidx.room.Entity

@Entity(tableName = "movies")
data class Movie(
    val adult: Boolean,
    val id: Long,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val img: String,
    val release: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genreId: Long
)
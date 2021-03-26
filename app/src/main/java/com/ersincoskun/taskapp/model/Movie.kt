package com.ersincoskun.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val uuid: Long,
    val adult: Boolean,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val img: String,
    val release: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genre: String
)
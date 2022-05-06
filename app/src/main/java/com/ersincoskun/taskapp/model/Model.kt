package com.ersincoskun.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Response(
    val page: Long?,
    val results: MutableList<Result>?
)

data class Result(
    val id:Long,
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

@Entity(tableName = "MOVIES")
data class Movie(
    val id: Long,
    val adult: Boolean,
    val language: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val img: String,
    val release: String,
    val voteAverage: Double,
    val voteCount: Long,
    val genre: String,
    val allPersonForSearch:String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}

data class Person(
    val cast: List<Cast>,
    val id:Long
)

data class Cast(
    val genre: Int,
    val name: String,
    val movieId:Long,
    val character:String?,
    val popularity: Double?,
    @SerializedName("profile_path")
    val imgUrl: String?
)


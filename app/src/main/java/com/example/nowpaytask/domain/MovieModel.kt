package com.example.nowpaytask.domain

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val video: Boolean? = null,
    val title: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    val backdropPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    val voteAverage: Long? = null,
    val id: Long,
    val adult: Boolean? = null,
    val voteCount: Int? = null,
    var page: Int,
    var genre_ids: List<String> = emptyList()
)

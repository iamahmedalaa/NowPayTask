package com.example.nowpaytask.data.remote

import com.example.nowpaytask.domain.MovieModel

data class MoviesResponseModel(
    val page: Int? = null,
    val totalPages: Int? = null,
    val results: List<MovieModel> = emptyList(),
    val totalResults: Int? = null
)
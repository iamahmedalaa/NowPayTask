package com.example.nowpaytask.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("/3/discover/movie")
    suspend fun fetchMoviesList(@Query("page") page: Int): MoviesResponseModel
}
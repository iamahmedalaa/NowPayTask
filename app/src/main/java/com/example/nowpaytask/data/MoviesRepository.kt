package com.example.nowpaytask.data

import com.example.nowpaytask.data.remote.MoviesApi
import com.example.nowpaytask.data.remote.MoviesResponseModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesApi: MoviesApi,
) {
    fun loadMovies() = flow {
        val result = moviesApi.fetchMoviesList(1)
        emit(result)
    }.catch {
        emit(MoviesResponseModel())
    }
}
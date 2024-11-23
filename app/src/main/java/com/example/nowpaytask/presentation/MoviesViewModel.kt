package com.example.nowpaytask.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowpaytask.domain.MovieModel
import com.example.nowpaytask.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repo: MoviesRepository,
) : ViewModel() {

    private val movies_ = MutableStateFlow(emptyList<MovieModel>())
     val movies: StateFlow<List<MovieModel>> = movies_.asStateFlow()

    init {
        viewModelScope.launch {
            repo.loadMovies().collectLatest {
                movies_.value = it.results
            }
        }
    }

    fun emptyState(){
        movies_.value = emptyList()
    }

}
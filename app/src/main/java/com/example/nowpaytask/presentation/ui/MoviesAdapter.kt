package com.example.nowpaytask.presentation.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nowpaytask.databinding.ItemMovieBinding
import com.example.nowpaytask.domain.MovieModel

class MoviesAdapter(
    private var movies: List<MovieModel> = emptyList(),
    private val onItemClick: () -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onItemClick = onItemClick)
    }

    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(
        movies: List<MovieModel>,
    ) {
        this.movies = movies
        notifyDataSetChanged()
    }
}

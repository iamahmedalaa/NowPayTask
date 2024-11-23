package com.example.nowpaytask.presentation.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.nowpaytask.databinding.ItemMovieBinding
import com.example.nowpaytask.domain.MovieModel

class MovieViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieModel, onItemClick: () -> Unit) {
        binding.tvTitle.text = movie.title ?: "Untitled"
        binding.tvOverview.text = movie.overview ?: "No overview available."
        binding.tvReleaseDate.text = movie.releaseDate ?: "Unknown Release Date"
        binding.root.setOnClickListener { onItemClick() }
    }
}
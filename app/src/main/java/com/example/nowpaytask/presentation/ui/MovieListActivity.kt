package com.example.nowpaytask.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nowpaytask.databinding.ActivityMovieListBinding
import com.example.nowpaytask.presentation.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private val myViewModel: MoviesViewModel by viewModels()
    private val adapter by lazy {
        MoviesAdapter(
            onItemClick = {
                onItemClick()
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initRecyclerView()
        lifecycleScope.launch {
            myViewModel.movies.collectLatest {
                binding.recyclerViewMovies.isVisible = it.isNotEmpty()
                adapter.setList(it)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewMovies.apply {
            layoutManager = LinearLayoutManager(this@MovieListActivity)
            adapter = this@MovieListActivity.adapter
        }

    }

    private fun onItemClick() {
        Toast.makeText(
            this@MovieListActivity,
            "on Item Clicked",
            Toast.LENGTH_SHORT
        ).show()
    }
}
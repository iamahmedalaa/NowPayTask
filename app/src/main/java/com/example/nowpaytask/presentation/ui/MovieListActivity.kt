package com.example.nowpaytask.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            myViewModel.movies.collectLatest {
                if (it.isEmpty()){
                   binding.recyclerViewMovies.isGone = true
                }else{
                    binding.recyclerViewMovies.isVisible = true
                }

                binding.recyclerViewMovies.apply {
                    layoutManager = LinearLayoutManager(this@MovieListActivity)
                    adapter = MoviesAdapter(it,
                        onItemClick = {
                            Toast.makeText(
                                this@MovieListActivity,
                                "on Item Clicked",
                                Toast.LENGTH_SHORT
                            ).show()
                        })
                }
            }
        }
    }
}
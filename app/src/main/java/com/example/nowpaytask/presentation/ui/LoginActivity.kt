package com.example.nowpaytask.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.nowpaytask.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var isValidEmail = false
        var isPasswordValid = false
        binding.apply {
            emailEt.addTextChangedListener {
                isValidEmail = if (it?.isBlank()?.not() == true) {
                    it.contains('@') || it.contains('.')
                } else {
                    false
                }
            }
            passwordEt.addTextChangedListener {
                isPasswordValid = it?.isBlank()?.not() == true && it.length >= 3
            }
            loginBt.setOnClickListener {

                if (isValidEmail.not()) {
                    emailErrorTv.isVisible = true
                } else if (isPasswordValid.not()) {
                    passwordErrorTv.isVisible = true
                } else {
                    val movieListActivity = Intent(
                        applicationContext,
                        MovieListActivity::class.java
                    )
                    startActivity(movieListActivity)
                }

            }
        }
    }
}
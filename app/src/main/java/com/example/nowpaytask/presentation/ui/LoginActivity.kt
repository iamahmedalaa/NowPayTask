package com.example.nowpaytask.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.example.nowpaytask.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isValidEmail = false
    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init() {
        binding.apply {
            emailEt.addTextChangedListener {
                isValidEmail =
                    it.isNullOrBlank().not() == true && (it!!.contains('@') || it.contains('.'))
            }
            passwordEt.addTextChangedListener {
                isPasswordValid = it?.isBlank()?.not() == true && it.length >= 3
            }
        }
        setOnClick()
    }

    private fun setOnClick() {
        binding.apply {
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
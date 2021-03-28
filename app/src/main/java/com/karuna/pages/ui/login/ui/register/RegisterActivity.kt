package com.karuna.pages.ui.login.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.MainActivity
import com.karuna.pages.R
import com.karuna.pages.ui.BaseActivity
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        registerUiListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    showLoadingIndicator(false)
                    startActivity(Intent(this, MainActivity::class.java))
                }
                Resource.Status.ERROR -> {
                    showLoadingIndicator(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    showLoadingIndicator(true)
                    Toast.makeText(this, "Loading!!!!!!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    /**
     * Register ui listeners
     */
    private fun registerUiListeners() {
        btnRegister.setOnClickListener {
            val name = nameTextView.text.toString()
            val email = txtUsername.text.toString()
            val password = txtPassword.text.toString()
            if (email.isBlank()) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            } else if (password.isBlank()) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(name,email, password)
            }
        }

    }

    private fun registerUser(name: String, email: String, password: String) {
        showLoadingIndicator(true)
        viewModel.registerUser(name, email, password)
    }
}


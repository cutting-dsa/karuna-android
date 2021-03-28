package com.karuna.pages.ui.login.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.MainActivity
import com.karuna.pages.R
import com.karuna.pages.ui.BaseActivity
import com.karuna.pages.ui.listings.ListingViewModel
import com.karuna.pages.ui.login.ui.register.RegisterActivity
import com.karuna.pages.utils.Resource
import com.karuna.pages.utils.gone
import com.karuna.pages.utils.visible
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
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
        txtLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val email = txtUsername.text.toString()
            val password = txtPassword.text.toString()
            if (email.isBlank()) {
                Toast.makeText(this, "Please enter your email",Toast.LENGTH_SHORT).show()
            } else if (password.isBlank()) {
                Toast.makeText(this, "Please enter your password",Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email,password)
            }
        }

    }

    private fun loginUser(email: String, password: String) {
        showLoadingIndicator(true)
        viewModel.loginUser(email, password)
    }
}
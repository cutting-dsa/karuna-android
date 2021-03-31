package com.karuna.pages.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.ui.login.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        setUpUI()
    }

    private fun setUpUI() {
        viewModel.fetchUser()

        viewModel.uiState.observe(this, Observer {
            txtName.text = it.name
            txtEmail.text = it.email
            txtRole.text = it.name
        })

        logoutBtn.setOnClickListener {
            viewModel.logoutUser()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
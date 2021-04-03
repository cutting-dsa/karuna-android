package com.karuna.pages.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
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
            txtName.text = "${it.firstName} ${it.lastName }"
            txtEmail.text = it?.username
            txtRole.text = it?.roles?.first()?.name
        })

        logoutBtn.setOnClickListener {
//            viewModel.logoutUser()
//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
        }
    }
}
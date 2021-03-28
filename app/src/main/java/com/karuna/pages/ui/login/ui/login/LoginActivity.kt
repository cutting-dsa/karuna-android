package com.karuna.pages.ui.login.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.ui.listings.ListingViewModel
import com.karuna.pages.utils.Resource

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
        fetchListings()
        setupObservers()
    }

    private fun fetchListings() {
        viewModel.fetchListings()
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
//                    binding.progressBar.visibility = View.GONE
                    //  if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    Toast.makeText(this, "Loading!!!!!!", Toast.LENGTH_SHORT).show()
//                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

}
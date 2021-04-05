package com.karuna.pages.ui.mylistings

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karuna.pages.R
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.utils.Resource.Status
import kotlinx.android.synthetic.main.activity_listings.*

class MyListingsActivity : BaseActivity() {
    private lateinit var viewModel: MyListingsViewModel

    private lateinit var tempListings: List<Listing>
    private lateinit var adapter: MyListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_listings)
        viewModel = ViewModelProvider(this).get(MyListingsViewModel::class.java)
        fetchListings()
        registerUiListeners()

    }

    private fun setUpView() {
        adapter = MyListingAdapter(tempListings)
        val layoutManager = LinearLayoutManager(applicationContext)
        listingsRecyclerView.itemAnimator = DefaultItemAnimator()
        listingsRecyclerView.layoutManager = layoutManager
        listingsRecyclerView.adapter = adapter

        swiperefresh.setOnRefreshListener {
            fetchListings()
        }
    }

    private fun fetchListings() {
        viewModel.fetchListings()
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            tempListings = it
                            setUpView()
                        }
                    }
                }
                Status.ERROR -> {
                    showLoadingIndicator(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {
                    showLoadingIndicator(true)
                    Toast.makeText(this, "Loading!!!!!!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}
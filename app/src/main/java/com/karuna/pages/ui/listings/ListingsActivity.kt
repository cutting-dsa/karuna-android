package com.karuna.pages.ui.listings

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karuna.pages.R
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_listings.*

class ListingsActivity : BaseActivity() {
    private lateinit var viewModel: ListingViewModel

    private lateinit var tempListings: List<Listing>
    private lateinit var adapter: ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listings)
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
        fetchListings()
        registerUiListeners()
    }

    private fun setUpView() {
        adapter = ListingAdapter(tempListings)
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
        showLoadingIndicator(true)
        viewModel.uiState.observe(this, Observer { it ->
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            tempListings = it
                            setUpView()
                        }
                    }
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

//    private fun navigateToListing(listing: Listing) {
//        val data = Intent().apply {
//            putExtra(Constants.question, listing)
//        }
//        startActivity(data)
//    }
//
//    override fun onCellClickListener(data: Listing) {
//        navigateToListing(data)
//    }

}
package com.karuna.pages.ui.listings.listing

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.listings.reviewlisting.ReviewListingActivity
import com.karuna.pages.utils.Constants
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_listing_detail.*

class ListingDetailActivity : BaseActivity() {

    private lateinit var viewModel: ListingDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing_detail)
        viewModel = ViewModelProvider(this).get(ListingDetailViewModel::class.java)
        fetchListing()
        setupObservers()
    }

    private fun fetchListing() {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val listingId = intent.extras?.getInt(Constants.listingId)
        listingId?.let { viewModel.fetchListing(it) }
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                   setUpUI(it.data)
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

    private fun setUpUI(listing: Listing?) {
        if (listing == null) return
        txtName.text = listing.listingname
        txtOwner.text = "${listing.listinguser.firstName} ${listing.listinguser.lastName}"
        ratingBar.rating = listing.averageRating.toFloat()

        reviewBtn.setOnClickListener {
            startActivity(Intent(this, ReviewListingActivity::class.java))
        }
    }
}
package com.karuna.pages.ui.listings.reviewlisting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.listings.ListingsActivity
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_review_listing.*

class ReviewListingActivity : BaseActivity() {
    private lateinit var viewModel: ReviewListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_listing)
        viewModel = ViewModelProvider(this).get(ReviewListingViewModel::class.java)
        setUpView()
        registerUiListeners()
    }

    private fun setUpView() {
        btnSubmit.setOnClickListener {
            createReview()
        }
    }

    private fun createReview() {
        val reviewComment = txtReview.text.toString()
        val rating = ratingBar.rating
        viewModel.reviewListing(reviewComment, rating.toInt(), 1)
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    Toast.makeText(this, "Review successfully submitted!!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, ListingsActivity::class.java))
                    finish()
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
}
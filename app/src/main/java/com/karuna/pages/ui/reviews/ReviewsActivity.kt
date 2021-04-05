package com.karuna.pages.ui.reviews

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karuna.pages.R
import com.karuna.pages.data.entities.*
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_reviews.*

class ReviewsActivity : BaseActivity(){
    private lateinit var viewModel: ReviewsViewModel

    private lateinit var tempReviews: List<Review>
    private lateinit var reviewsAdapter: ReviewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        viewModel = ViewModelProvider(this).get(ReviewsViewModel::class.java)
        fetchReviews()
        registerUiListeners()
    }

    private fun setUpView() {
        reviewsAdapter = ReviewAdapter(tempReviews)
        val layoutManager = LinearLayoutManager(applicationContext)
        reviewsRecyclerView.itemAnimator = DefaultItemAnimator()
        reviewsRecyclerView.layoutManager = layoutManager
        reviewsRecyclerView.adapter = reviewsAdapter

        swiperefresh.apply {
            setColorSchemeResources(*refreshColors)
            isRefreshing = false
            setOnRefreshListener {
                //viewModel.fetchReviews()
            }
        }
    }

    private fun fetchReviews() {
        viewModel.fetchReviews()
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            tempReviews = it
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

    companion object {
        /**
         * Slide Refresh view colors
         */
        val refreshColors: IntArray

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
            refreshColors = intArrayOf(R.color.colorPrimaryDark, android.R.color.holo_green_dark,
                R.color.colorAccent, android.R.color.holo_purple, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_dark)
        }

    }
}
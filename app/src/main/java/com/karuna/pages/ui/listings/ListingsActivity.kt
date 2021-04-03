package com.karuna.pages.ui.listings

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karuna.pages.R
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.entities.Role
import com.karuna.pages.data.entities.User
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
        addListings()
        setUpView()
        registerUiListeners(true)
       // fetchListings()
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

    private fun addListings() {
        tempListings = listOf(
            Listing("Karuna Restaurant",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant1",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant2",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant3",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant4",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant5",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants")),
            Listing("Karuna Restaurant6",1,
                "125343 N 6th st fairfield IA 5227","",1,
                User("Johnstone","",1,"otoyo",listOf(Role("",""))),
            Category("","Restaurants"))
        )
    }

    private fun registerUiListeners(showIndicator: Boolean) {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                    if (showIndicator) showLoadingIndicator(false)
                }
                Resource.Status.ERROR -> {
                    if (showIndicator) showLoadingIndicator(false)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                Resource.Status.LOADING -> {
                    if (showIndicator) showLoadingIndicator(true)
                    Toast.makeText(this, "Loading!!!!!!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


}
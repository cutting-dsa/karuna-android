package com.karuna.pages.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karuna.pages.R
import com.karuna.pages.ui.reviews.ReviewsActivity
import com.karuna.pages.ui.listings.ListingsActivity
import com.karuna.pages.ui.mylistings.MyListingsActivity
import com.karuna.pages.ui.profile.ProfileActivity
import com.karuna.pages.ui.questions.QuestionsActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerViewActions()
    }

    private fun registerViewActions() {
        listingsLayout.setOnClickListener {
            startActivity(Intent(this, ListingsActivity::class.java))
        }

        favoritesLayout.setOnClickListener {
            startActivity(Intent(this, ReviewsActivity::class.java))
        }

        profileLayout.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        myListingsLayout.setOnClickListener {
            startActivity(Intent(this, MyListingsActivity::class.java))
        }

        questionsLayout.setOnClickListener {
            startActivity(Intent(this, QuestionsActivity::class.java))
        }
    }

}
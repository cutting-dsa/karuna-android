package com.karuna.pages.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karuna.pages.R
import com.karuna.pages.ui.QuestionsActivity
import com.karuna.pages.ui.claims.ClaimsActivity
import com.karuna.pages.ui.favorites.FavoritesActivity
import com.karuna.pages.ui.listings.ListingsActivity
import com.karuna.pages.ui.mylistings.MyListingsActivity
import com.karuna.pages.ui.profile.ProfileActivity
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

        listingClaimsLayout.setOnClickListener {
            startActivity(Intent(this, ClaimsActivity::class.java))
        }

        favoritesLayout.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
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
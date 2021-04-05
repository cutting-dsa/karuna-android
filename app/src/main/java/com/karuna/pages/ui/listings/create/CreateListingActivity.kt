package com.karuna.pages.ui.listings.create

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.listings.ListingsActivity
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_create_listing.*

class CreateListingActivity: BaseActivity() {
    private lateinit var viewModel: CreateListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_listing)
        viewModel = ViewModelProvider(this).get(CreateListingViewModel::class.java)
        fetchCategories()
        registerUiListeners()
        registerCategoryListeners()
    }

    private fun setUpView() {
        categories_spinner.setSelection(0)

//        categories_spinner.onItemSelectedListener = this

        btnSubmit.setOnClickListener {
            createQuestion()
        }
    }

    private fun createQuestion() {
//        val questiontext = txtQuestion.text.toString()
//        val categoryId = categories_spinner.selectedItemId
//        viewModel.createQuestion(categoryId.toInt(), questiontext)
    }

    private fun fetchCategories() {
        viewModel.fetchCategories()
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    Toast.makeText(this, "Listing successfully submitted!!", Toast.LENGTH_SHORT)
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

    private fun registerCategoryListeners() {
        viewModel.categoryUIState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
//                            val adapter = SpinnerAdapter(this, it)
//                            categories_spinner.adapter = adapter
//                            setUpView()
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
}
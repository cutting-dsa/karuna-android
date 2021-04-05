package com.karuna.pages.ui.questions

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.data.entities.Category
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.base.SpinnerAdapter
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_create_question.*

class CreateQuestionActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var viewModel: CreateQuestionViewModel

    var tempCategories: List<Category>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_question)
        viewModel = ViewModelProvider(this).get(CreateQuestionViewModel::class.java)
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
        val questiontext = txtQuestion.text.toString()
        val categoryId = categories_spinner.selectedItemId
        viewModel.createQuestion(categoryId.toInt(), questiontext)
    }

    private fun fetchCategories() {
        viewModel.fetchCategories()
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    Toast.makeText(this, "Question successfully submitted!!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this, QuestionsActivity::class.java))
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
                            val adapter = SpinnerAdapter(this, it)
                            categories_spinner.adapter = adapter
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


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

//        if (tempCategories != null) {
//            visits = ArrayList()
//            if (spinner.selectedItemPosition == 0) {
//                for (visit in visitsAll as List) {
//                    if (visit.exitTime == null) {
//                        visits!!.add(visit)
//
//                    }
//                }
//            } else {
//                for (visit in visitsAll as List) {
//                    if (visit.exitTime != null) {
//                        visits!!.add(visit)
//
//                    }
//                }
//
//            }
//            adapter?.update(visits)
//        }
    }
}
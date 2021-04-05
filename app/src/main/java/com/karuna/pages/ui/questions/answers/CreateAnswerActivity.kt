package com.karuna.pages.ui.questions.answers

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karuna.pages.R
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.questions.QuestionsActivity
import com.karuna.pages.utils.Constants
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_create_answer.*

class CreateAnswerActivity : BaseActivity() {
    private lateinit var viewModel: CreateAnswerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_answer)
        viewModel = ViewModelProvider(this).get(CreateAnswerViewModel::class.java)
        registerUiListeners()
    }

    private fun registerUiListeners() {
        btnSubmit.setOnClickListener {
            val answerText = txtAnswer.text.toString()
            viewModel.createAnswer(answerText, fetchId())
        }

        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            Toast.makeText(this, "Answer posted successfully", Toast.LENGTH_SHORT)
                                .show()
                            startActivity(Intent(this, QuestionsActivity::class.java))
                            finish()
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

    private fun fetchId(): Int {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val questionId = intent.extras?.getInt(Constants.questionId)
        return questionId!!
    }
}
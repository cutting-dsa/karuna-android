package com.karuna.pages.ui.questions.answers

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
import com.karuna.pages.ui.questions.QuestionsViewModel
import com.karuna.pages.utils.Constants
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_answers.*

class AnswersActivity: BaseActivity(){
    private lateinit var viewModel: QuestionsViewModel

    private lateinit var tempAnswers: List<Answer>
    private lateinit var adapter: AnswersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)
        fetchAnswers()
        registerUiListeners()
    }

    private fun setUpView() {
        adapter = AnswersAdapter(tempAnswers)
        val layoutManager = LinearLayoutManager(applicationContext)
        answersRecyclerView.itemAnimator = DefaultItemAnimator()
        answersRecyclerView.layoutManager = layoutManager
        answersRecyclerView.adapter = adapter

        val question: Question = tempAnswers.first().question
        txtQuestion.text = question.name

        swiperefresh.apply {
            setColorSchemeResources(*refreshColors)
            isRefreshing = false
            setOnRefreshListener(::fetchAnswers)
        }
    }

    private fun fetchAnswers() {
        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        val questionId = intent.extras?.getInt(Constants.questionId)
        questionId?.let { viewModel.fetchAnswers(it) }
    }

    private fun addAnswers() {
        tempAnswers = listOf(
            Answer("0","sdsds", user = User(1,"Johnstone","",1,"otoyo",listOf(Role("",""))),
            question = Question(1,"lorem ipsum",true, Category("","Restaurants"),"10-10-2021"),
            created_at = "10-10-2021"))
    }

    private fun registerUiListeners() {
        viewModel.answerUIState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            tempAnswers = it
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
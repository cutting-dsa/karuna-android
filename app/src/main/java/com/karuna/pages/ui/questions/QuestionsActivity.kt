package com.karuna.pages.ui.questions

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.karuna.pages.R
import com.karuna.pages.data.entities.Question
import com.karuna.pages.ui.base.BaseActivity
import com.karuna.pages.ui.questions.answers.AnswersActivity
import com.karuna.pages.utils.Constants
import com.karuna.pages.utils.OnItemClickListener
import com.karuna.pages.utils.Resource
import kotlinx.android.synthetic.main.activity_listings.*
import kotlinx.android.synthetic.main.activity_listings.swiperefresh
import kotlinx.android.synthetic.main.activity_questions.*


class QuestionsActivity : BaseActivity() {
    private lateinit var viewModel: QuestionsViewModel

    private lateinit var tempQuestions: List<Question>
    private lateinit var adapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)
        fetchQuestions()
        registerUiListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                val intent = Intent(
                    this@QuestionsActivity,
                    CreateQuestionActivity::class.java
                )
                startActivity(intent)
            }
            else -> {
            }
        }
        return true
    }

    private fun setUpView() {
        adapter = QuestionsAdapter(tempQuestions)
        val layoutManager = LinearLayoutManager(applicationContext)
        questionsRecyclerView.itemAnimator = DefaultItemAnimator()
        questionsRecyclerView.layoutManager = layoutManager
        questionsRecyclerView.adapter = adapter
        adapter.listener = object : OnItemClickListener<Question> {
            override fun invoke(question: Question) {

                val intent = Intent(
                    this@QuestionsActivity,
                    AnswersActivity::class.java
                ).apply {
                    putExtra(Constants.questionId, question.id)
                }
                startActivity(intent)
            }
        }

        swiperefresh.apply {
            setColorSchemeResources(*refreshColors)
            isRefreshing = false
            setOnRefreshListener {
                viewModel.fetchQuestions()
            }
        }
    }

    private fun fetchQuestions() {
        viewModel.fetchQuestions()
    }

    private fun registerUiListeners() {
        viewModel.uiState.observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    showLoadingIndicator(false)
                    it.data.also {
                        if (it != null) {
                            tempQuestions = it
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
            refreshColors = intArrayOf(
                R.color.colorPrimaryDark, android.R.color.holo_green_dark,
                R.color.colorAccent, android.R.color.holo_purple, android.R.color.holo_red_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_dark
            )
        }

    }
}
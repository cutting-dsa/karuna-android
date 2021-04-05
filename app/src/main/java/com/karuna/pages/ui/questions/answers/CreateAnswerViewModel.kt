package com.karuna.pages.ui.questions.answers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Answer
import com.karuna.pages.data.repository.QuestionsRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class CreateAnswerViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = QuestionsRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<Answer>> = MutableLiveData()


    fun createAnswer(answerText: String, questionId: Int) {
        viewModelScope.launch {
            uiState.value = repository.createAnswer(questionId, answerText)
        }
    }
}
package com.karuna.pages.ui.questions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Answer
import com.karuna.pages.data.entities.Question
import com.karuna.pages.data.repository.QuestionsRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class QuestionsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = QuestionsRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<List<Question>>> = MutableLiveData()
    val answerUIState: MutableLiveData<Resource<List<Answer>>> = MutableLiveData()

    val questionState: MutableLiveData<Int> = MutableLiveData()

    fun fetchQuestions() {
        viewModelScope.launch {
            uiState.value = repository.getQuestions()
        }
    }

    fun fetchAnswers(id: Int) {
        viewModelScope.launch {
            answerUIState.value = repository.getAnswers(id.toLong())
        }
    }

    fun setQuestionId(id: Int) {
        questionState.value = id
    }

}
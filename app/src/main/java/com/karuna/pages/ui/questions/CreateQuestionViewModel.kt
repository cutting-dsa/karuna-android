package com.karuna.pages.ui.questions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.entities.Question
import com.karuna.pages.data.repository.CategoryRepository
import com.karuna.pages.data.repository.QuestionsRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class CreateQuestionViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = QuestionsRepository(application.applicationContext)
    private var categoryRepository = CategoryRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<Question>> = MutableLiveData()
    val categoryUIState: MutableLiveData<Resource<List<Category>>> = MutableLiveData()

    fun createQuestion(categoryId: Int,question: String) {
        viewModelScope.launch {
            uiState.value = repository.createQuestion(categoryId, question)
        }
    }

    fun fetchCategories() {
        viewModelScope.launch {
            categoryUIState.value = categoryRepository.getCategories()
        }
    }
}
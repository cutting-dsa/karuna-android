package com.karuna.pages.ui.reviews

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Answer
import com.karuna.pages.data.entities.Question
import com.karuna.pages.data.entities.Review
import com.karuna.pages.data.repository.QuestionsRepository
import com.karuna.pages.data.repository.ReviewRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class ReviewsViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = ReviewRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<List<Review>>> = MutableLiveData()
    val answerUIState: MutableLiveData<Resource<List<Review>>> = MutableLiveData()

    fun fetchReviews() {
        viewModelScope.launch {
            uiState.value = repository.getReviews()
        }
    }

//    fun fetchAnswers(id: Int) {
//        viewModelScope.launch {
//            answerUIState.value = repository.getReviews(id.toLong())
//        }
//    }
}
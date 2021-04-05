package com.karuna.pages.ui.listings.create

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.repository.CategoryRepository
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class CreateListingViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = ListingRepository(application.applicationContext)
    private var categoryRepository = CategoryRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<Listing>> = MutableLiveData()
    val categoryUIState: MutableLiveData<Resource<Category>> = MutableLiveData()

    fun createQuestion(categoryId: Int,question: String) {
//        viewModelScope.launch {
//            uiState.value = repository.createListing(categoryId, question)
//        }
    }

    fun fetchCategories() {
//        viewModelScope.launch {
//            categoryUIState.value = categoryRepository.getCategories()
//        }
    }
}
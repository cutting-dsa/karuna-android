package com.karuna.pages.ui.listings.reviewlisting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Review
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class ReviewListingViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = ListingRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<Review>> = MutableLiveData()


    fun reviewListing(comment: String,rating: Int, listingId: Int) {
        viewModelScope.launch {
            uiState.value = repository.reviewListing(comment,rating,listingId)
        }
    }
}
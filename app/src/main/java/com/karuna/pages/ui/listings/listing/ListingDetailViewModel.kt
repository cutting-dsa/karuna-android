package com.karuna.pages.ui.listings.listing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class ListingDetailViewModel (application: Application) : AndroidViewModel(application) {
    private var repository = ListingRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<Listing>> = MutableLiveData()

    fun fetchListing(id: Int) {
        viewModelScope.launch {
            uiState.value = repository.getListing(id)
        }
    }
}
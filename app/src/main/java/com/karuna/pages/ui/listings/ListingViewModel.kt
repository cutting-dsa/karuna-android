package com.karuna.pages.ui.listings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class ListingViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = ListingRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<List<Listing>>> = MutableLiveData()

    fun fetchListings() {
        viewModelScope.launch {
            uiState.value = repository.getListings()
        }
    }
}
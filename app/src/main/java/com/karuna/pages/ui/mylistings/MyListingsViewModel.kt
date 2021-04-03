package com.karuna.pages.ui.mylistings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class MyListingsViewModel(application: Application): AndroidViewModel(application) {
    private var repository = ListingRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<List<Listing>>> = MutableLiveData()

    fun fetchListings() {

        viewModelScope.launch {
            uiState.value = repository.getMyListings()
        }
    }
}
package com.karuna.pages.ui.listings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.Entries
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.repository.ListingRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch
import timber.log.Timber

class ListingViewModel : ViewModel() {
    private var repository = ListingRepository()

    val uiState: MutableLiveData<Resource<List<Listing>>> = MutableLiveData()

    fun fetchListings() {

        viewModelScope.launch {
            uiState.value = repository.getListings()
        }
    }
}
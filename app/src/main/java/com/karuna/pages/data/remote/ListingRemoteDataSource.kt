package com.karuna.pages.data.remote

import com.karuna.pages.data.network.RestClient
import com.karuna.pages.utils.PreferenceManager

class ListingRemoteDataSource constructor(preferenceManager: PreferenceManager): BaseDataSource() {
    private val prefManager: PreferenceManager = preferenceManager
    suspend fun getListings() = getResult { RestClient.getInstance(prefManager).getApiService().getListings() }
    suspend fun getMyListings() = getResult { RestClient.getInstance(prefManager).getApiService().getMyListings() }
}
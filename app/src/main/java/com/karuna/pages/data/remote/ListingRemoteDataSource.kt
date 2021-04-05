package com.karuna.pages.data.remote

import com.karuna.pages.data.entities.Review
import com.karuna.pages.data.network.RestClient
import com.karuna.pages.utils.PreferenceManager
import okhttp3.Credentials

class ListingRemoteDataSource constructor(preferenceManager: PreferenceManager) : BaseDataSource() {
    private val prefManager: PreferenceManager = preferenceManager
    private var credentials: String = Credentials.basic("karuna", "password")
    suspend fun getListings() =
        getResult { RestClient.getInstance(prefManager).getApiService().getListings(credentials) }

    suspend fun getListing(id: Long) = getResult { RestClient.getInstance(prefManager).getApiService().getListing(credentials,id) }

    suspend fun getMyListings() = getResult {
        RestClient.getInstance(prefManager).getApiService().getMyListings(credentials, 1)
    }

    suspend fun reviewListing(review: Review) = getResult {
        RestClient.getInstance(prefManager).getApiService().reviewListing(credentials, review)
    }
}
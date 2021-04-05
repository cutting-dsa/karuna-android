package com.karuna.pages.data.remote

import com.karuna.pages.data.network.RestClient
import com.karuna.pages.utils.PreferenceManager
import okhttp3.Credentials

class CategoryRemoteDataSource constructor(preferenceManager: PreferenceManager): BaseDataSource() {
    private val prefManager: PreferenceManager = preferenceManager
    private var credentials: String = Credentials.basic("karuna", "password")
    suspend fun getCategories() = getResult { RestClient.getInstance(prefManager).getApiService().getCategories(credentials) }
}
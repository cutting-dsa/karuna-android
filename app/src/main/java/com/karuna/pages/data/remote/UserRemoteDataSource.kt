package com.karuna.pages.data.remote

import com.karuna.pages.data.network.RestClient
import com.karuna.pages.utils.PreferenceManager

class UserRemoteDataSource(preferenceManager: PreferenceManager) : BaseDataSource() {
    private val prefManager: PreferenceManager = preferenceManager
    suspend fun loginUser(email: String, password: String) =
        getResult { RestClient.getInstance(prefManager, false).getApiService().login(email, password) }

    suspend fun registerUser(name: String, email: String, password: String) =
        getResult { RestClient.getInstance(prefManager).getApiService().register(name, email, password) }
}
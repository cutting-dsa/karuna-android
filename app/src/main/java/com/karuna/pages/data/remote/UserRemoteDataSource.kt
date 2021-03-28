package com.karuna.pages.data.remote

import com.karuna.pages.data.network.RestClient

class UserRemoteDataSource : BaseDataSource() {
    suspend fun loginUser(email: String, password: String) =
        getResult { RestClient.getInstance().getApiService().login(email, password) }

    suspend fun registerUser(name: String, email: String, password: String) =
        getResult { RestClient.getInstance().getApiService().register(name, email, password) }
}
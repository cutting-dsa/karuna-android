package com.karuna.pages.data.repository

import com.karuna.pages.data.entities.User
import com.karuna.pages.data.remote.UserRemoteDataSource
import com.karuna.pages.utils.Resource

class UserRepository {

    private var remoteDataSource: UserRemoteDataSource = UserRemoteDataSource()

    suspend fun loginUser(email: String, password: String): Resource<User> =
        remoteDataSource.loginUser(email, password)

    suspend fun registerUser(name: String, email: String, password: String): Resource<User> =
        remoteDataSource.registerUser(name, email, password)

}
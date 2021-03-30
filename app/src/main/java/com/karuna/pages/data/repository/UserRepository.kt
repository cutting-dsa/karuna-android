package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.remote.UserRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class UserRepository constructor(context: Context) {

    private var remoteDataSource: UserRemoteDataSource = UserRemoteDataSource()
    private var preferenceManager: PreferenceManager = PreferenceManager(context)

    suspend fun loginUser(email: String, password: String): Resource<User> =
        remoteDataSource.loginUser(email, password)

    suspend fun registerUser(name: String, email: String, password: String): Resource<User> =
        remoteDataSource.registerUser(name, email, password)

    var user: User? = preferenceManager.user

    fun saveUser(user: User) {
        preferenceManager.user = user
    }

}
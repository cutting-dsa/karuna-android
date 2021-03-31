package com.karuna.pages.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.repository.UserRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = UserRepository(application.applicationContext)

    val uiState: MutableLiveData<User> = MutableLiveData()

    fun fetchUser() {
        uiState.value = repository.user
    }

    fun logoutUser() {
        repository.logoutUser()
    }
}
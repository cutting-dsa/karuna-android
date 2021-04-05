package com.karuna.pages.ui.login.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.repository.UserRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = UserRepository(application.applicationContext)

    val uiState: MutableLiveData<Resource<User>> = MutableLiveData()

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val response = repository.loginUser(username, password)
            repository.user = response.data
            print("********${response.data}****")
            repository.savePass(password)
            saveLoginStatus(response.status)
            uiState.value = response
        }
    }

    private fun saveLoginStatus(status: Resource.Status) {
        when (status) {
            Resource.Status.SUCCESS -> {
                repository.isUserLoggedIn(true)
            }
            else -> {
                repository.isUserLoggedIn(false)
            }
        }
    }
}
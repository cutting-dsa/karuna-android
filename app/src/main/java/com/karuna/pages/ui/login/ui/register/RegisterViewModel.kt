package com.karuna.pages.ui.login.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.repository.UserRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = UserRepository(application)

    val uiState: MutableLiveData<Resource<User>> = MutableLiveData()

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            val response = repository.registerUser(name, email, password)
            repository.user = response.data
            uiState.value = response

        }
    }
}
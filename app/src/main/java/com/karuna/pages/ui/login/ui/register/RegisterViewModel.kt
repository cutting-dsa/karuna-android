package com.karuna.pages.ui.login.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.repository.UserRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private var repository = UserRepository()

    val uiState: MutableLiveData<Resource<User>> = MutableLiveData()

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            //save user in local db here or in repository and set logged in to true
            uiState.value = repository.registerUser(name, email, password)
        }
    }
}
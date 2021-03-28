package com.karuna.pages.ui.login.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karuna.pages.data.entities.User
import com.karuna.pages.data.repository.UserRepository
import com.karuna.pages.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private var repository = UserRepository()

    val uiState: MutableLiveData<Resource<User>> = MutableLiveData()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            //save user in local db here or in repository and set logged in to true
            uiState.value = repository.loginUser(email, password)
        }
    }
}
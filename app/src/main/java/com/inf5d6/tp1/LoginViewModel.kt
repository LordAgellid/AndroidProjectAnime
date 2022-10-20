package com.inf5d6.tp1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(val app: Application) : AndroidViewModel(app) {
    var username = ""
    var password = ""
    var isValid = MutableLiveData<Boolean>(null)

    fun getToken() {
        viewModelScope.launch(Dispatchers.IO) {
            val loginRepository = LoginRepository(getApplication())
            loginRepository.login(username, password, isValid)
        }
    }
}

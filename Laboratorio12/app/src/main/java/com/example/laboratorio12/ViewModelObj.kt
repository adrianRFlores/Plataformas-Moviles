package com.example.laboratorio12

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class ViewModelObj : ViewModel() {

    sealed interface LoginState {
        object Default: LoginState
        object Success: LoginState
        object Loading: LoginState
        class Error(val message: String): LoginState
    }

    private val _validAuthToken = MutableStateFlow(false)
    private val _loginStatus = MutableStateFlow<LoginState>(LoginState.Default)
    val loginStatus: StateFlow<LoginState> = _loginStatus
    val validAuthToken: StateFlow<Boolean> = _validAuthToken

    private lateinit var jobVar: Job

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginStatus.value = LoginState.Loading
            delay(2000L)
            if (email == "flo21500@uvg.edu.gt" && email == password) {
                _loginStatus.value = LoginState.Success
            } else {
                _loginStatus.value = LoginState.Error("Verifique los datos ingresados de nuevo")
            }

            _loginStatus.value = LoginState.Default
        }
    }

    fun logout() {
        jobVar.cancel()
        _validAuthToken.value = false
    }

    fun generateToken() {
        jobVar = viewModelScope.launch {
            _validAuthToken.value = true
            delay(10000L)
            _validAuthToken.value = false
        }
    }

    fun cancel() {
        jobVar.cancel()
    }

}
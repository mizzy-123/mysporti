package com.manifestasi.mysporty.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val excersiseRepository: ExcersiseRepository
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password = _password

    fun onEmailChange(email: String){
        _email.value = email
    }

    fun onPasswordChange(password: String){
        _password.value = password
    }

    private val _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _loadingLogin = MutableStateFlow(false)
    val loadingLogin = _loadingLogin

    fun login(){
        viewModelScope.launch {
            excersiseRepository.login(
                email = _email.value,
                password = _password.value
            ).collect { event ->
                when(event){
                    is Resource.Loading -> {
                        _loadingLogin.value = true
                    }
                    is Resource.Success -> {
                        _loadingLogin.value = false
                        _loginSuccess.value = true
                    }
                    is Resource.Error -> {
                        _loadingLogin.value = false
                        _toastMessage.send(event.errorMessage)
                    }
                    else -> {}
                }
            }
        }
    }

    fun resetLoginSuccess(){
        _loginSuccess.value = false
    }
}
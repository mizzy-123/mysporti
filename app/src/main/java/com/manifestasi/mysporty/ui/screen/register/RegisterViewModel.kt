package com.manifestasi.mysporty.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val excersiseRepository: ExcersiseRepository
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _firstName = MutableStateFlow("")
    val firstName: Flow<String> = _firstName

    private val _lastName = MutableStateFlow("")
    val lastName: Flow<String> = _lastName

    private val _password = MutableStateFlow("")
    val password = _password

    private val _loadingRegister = MutableStateFlow(false)
    val loadingRegister = _loadingRegister

    private val _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess = _registerSuccess.asStateFlow()

    fun onEmailChange(email: String){
        _email.value = email
    }

    fun onFirstNameChange(firstName: String){
        _firstName.value = firstName
    }

    fun onLastNameChange(lastName: String){
        _lastName.value = lastName
    }

    fun onPasswordChange(password: String){
        _password.value = password
    }

    fun register(){
        viewModelScope.launch {
            excersiseRepository.register(
                firstName = _firstName.value,
                email = _email.value,
                lastName = _lastName.value,
                password = _password.value
            ).collect {event ->
                when (event){
                    is Resource.Loading -> {
                        _loadingRegister.value = true
                    }
                    is Resource.Success -> {
                        _loadingRegister.value = false
                        _registerSuccess.value = true
                    }
                    is Resource.Error -> {
                        _toastMessage.send(event.errorMessage)
                    }
                    else -> {}
                 }
            }
        }
    }

    fun resetRegisterSuccess(){
        _registerSuccess.value = false
    }
}
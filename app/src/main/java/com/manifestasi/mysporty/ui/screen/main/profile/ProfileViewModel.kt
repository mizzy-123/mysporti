package com.manifestasi.mysporty.ui.screen.main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val excersiseRepository: ExcersiseRepository
) : ViewModel() {

    private var _loadingLogout = MutableStateFlow(false)
    val loadingLogout: Flow<Boolean> = _loadingLogout

    private var _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    private var _logoutSuccess = MutableStateFlow(false)
    val logoutSuccess: Flow<Boolean> = _logoutSuccess

    fun logout(){
        viewModelScope.launch {
            excersiseRepository.logout().collect { event ->
                when (event){
                    is Resource.Loading -> {
                        _loadingLogout.value = true
                    }
                    is Resource.Success -> {
                        _loadingLogout.value = false
                        _logoutSuccess.value = true
                    }
                    else -> {
                        _loadingLogout.value = false
                    }
                }
            }
        }
    }
}
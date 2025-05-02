package com.manifestasi.mysporty.ui.screen.main.profile.personaldata

import android.provider.ContactsContract.Profile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor(
    val excersiseRepository: ExcersiseRepository
): ViewModel() {

    private val _loadingUpdateProfile = MutableStateFlow<Boolean>(false)
    val loadingUpdateProfile: StateFlow<Boolean> = _loadingUpdateProfile

    private val _updateProfileSuccess = MutableStateFlow<Boolean>(false)
    val updateProfileSuccess: StateFlow<Boolean> = _updateProfileSuccess

    private val _successMessageUpdateProfile = Channel<String>()
    val successMessageUpdateProfile = _successMessageUpdateProfile.receiveAsFlow()

    private val _errorMessageUpdateProfile = Channel<String>()
    val errorMessageUpdateProfile = _errorMessageUpdateProfile.receiveAsFlow()

    private val _height = MutableStateFlow("")
    val height: StateFlow<String> = _height

    private val _weight = MutableStateFlow("")
    val weight: StateFlow<String> = _weight

    private val _age = MutableStateFlow("")
    val age: StateFlow<String> = _age

    private val _firstname = MutableStateFlow("")
    val firstname: StateFlow<String> = _firstname

    private val _lastname = MutableStateFlow("")
    val lastname: StateFlow<String> = _lastname

    fun onChangeHeight(height: String){
        _height.value = height
    }

    fun onChangeWeight(weight: String){
        _weight.value = weight
    }

    fun onChangeAge(age: String){
        _age.value = age
    }

    fun onChangeFirstName(firstName: String){
        _firstname.value = firstName
    }

    fun onChangeLastName(lastName: String){
        _lastname.value = lastName
    }

    fun updateProfile(){
        viewModelScope.launch {
            excersiseRepository.updateProfileUser(
                _height.value.toInt(),
                _weight.value.toInt(),
                _age.value.toInt(),
                _firstname.value,
                _lastname.value
            ).collect { event ->
                when(event){
                    is Resource.Loading -> {
                        _loadingUpdateProfile.value = true
                    }
                    is Resource.Success -> {
                        _loadingUpdateProfile.value = false
                        _updateProfileSuccess.value = true
                        _successMessageUpdateProfile.send("Berhasil di update")
                    }
                    is Resource.Error -> {
                        _loadingUpdateProfile.value = false
                        _errorMessageUpdateProfile.send(event.errorMessage)
                    }
                    else -> {
                        _loadingUpdateProfile.value = false
                        _errorMessageUpdateProfile.send("Something Wrong")
                    }
                }
            }
        }
    }
}
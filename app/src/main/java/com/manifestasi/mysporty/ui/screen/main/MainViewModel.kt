package com.manifestasi.mysporty.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.Profile
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val excersiseRepository: ExcersiseRepository
): ViewModel() {
    private val _profile = MutableStateFlow<Profile?>(null)
    val profile: Flow<Profile?> = _profile

    private val _loadingProfile = MutableStateFlow<Boolean>(false)
    val loadingProfile: Flow<Boolean> = _loadingProfile

    init {
        fetchProfile()
    }

    private fun fetchProfile(){
        viewModelScope.launch {
            excersiseRepository.getProfileUser().collect { event ->
                when (event){
                    is Resource.Loading -> {
                        _loadingProfile.value = true
                    }
                    is Resource.Success -> {
                        _profile.value = event.data
                        _loadingProfile.value = false
                    }
                    else -> {
                        _loadingProfile.value = false
                        _profile.value = null
                    }
                }
            }
        }
    }
}
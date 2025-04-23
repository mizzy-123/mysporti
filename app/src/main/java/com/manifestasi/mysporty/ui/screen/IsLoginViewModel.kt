package com.manifestasi.mysporty.ui.screen

import androidx.lifecycle.ViewModel
import com.manifestasi.mysporty.data.repository.ExcersiseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IsLoginViewModel @Inject constructor(
    private val excersiseRepository: ExcersiseRepository
): ViewModel() {

    fun isLoggedIn() = excersiseRepository.isLoggedIn()
}
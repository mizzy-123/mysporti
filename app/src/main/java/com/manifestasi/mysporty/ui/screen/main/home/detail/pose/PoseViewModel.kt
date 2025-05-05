package com.manifestasi.mysporty.ui.screen.main.home.detail.pose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoseViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: Flow<Boolean> = _isLoading

    private val _successSave = MutableStateFlow(false)
    val successSave: StateFlow<Boolean> = _successSave

    private val _errorSave = MutableStateFlow(false)
    val errorSave: StateFlow<Boolean> = _errorSave

    private val _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    fun errorSaveOnChange(value: Boolean){
        _errorSave.value = value
    }

    fun successSaveOnChange(value: Boolean){
        _successSave.value = value
    }

   fun saveExcersise(
       image_name: String,
       name: String,
       repetisi: Int
   ){
       viewModelScope.launch {
           historyRepository.insertHistoryExcersise(
               image_name, name, repetisi
           ).collect { event ->
               when(event){
                   is Resource.Loading -> {
                       _isLoading.value = true
                   }
                   is Resource.Success -> {
                       _isLoading.value = false
                       _successSave.value = true
                       _toastMessage.send("Berhasil disimpan")
                   }
                   is Resource.Error -> {
                       _isLoading.value = false
                       _toastMessage.send(event.errorMessage)
                       _errorSave.value = true
                   }
                   else -> {
                       _errorSave.value = true
                       _isLoading.value = false
                   }
               }
           }
       }
   }
}
package com.manifestasi.mysporty.ui.screen.main.profile.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.HistoryExcersise2
import com.manifestasi.mysporty.data.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
): ViewModel() {

//    init {
//        fetchHistoryExcersise()
//    }

    private val _dataHistoryExcersise = MutableStateFlow<List<HistoryExcersise2>>(emptyList())
    val dataHistoryExcersise: StateFlow<List<HistoryExcersise2>> = _dataHistoryExcersise

    private val _loadingHistoryExcersise = MutableStateFlow<Boolean>(false)
    val loadingHistoryExcersise: StateFlow<Boolean> = _loadingHistoryExcersise

    private val _successHistoryExcersise = MutableStateFlow<Boolean>(false)
    val successHistoryExcersise: StateFlow<Boolean> = _successHistoryExcersise

    private val _errorHistoryExcersise = MutableStateFlow<Boolean>(false)
    val errorHistoryExcersise: StateFlow<Boolean> = _errorHistoryExcersise

    private val _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    fun fetchHistoryExcersise(){
        viewModelScope.launch {
            historyRepository.getHistoryExcersise().collect { event ->
                when (event){
                    is Resource.Loading -> {
                        _loadingHistoryExcersise.value = true
                    }
                    is Resource.Success -> {
                        _successHistoryExcersise.value = true
                        _dataHistoryExcersise.value = event.data
                    }
                    is Resource.Error -> {
                        _toastMessage.send(event.errorMessage)
                        _errorHistoryExcersise.value = true
                        _loadingHistoryExcersise.value = false
                    }
                    else -> {
                        _loadingHistoryExcersise.value = false
                    }
                }
            }
        }
    }
}
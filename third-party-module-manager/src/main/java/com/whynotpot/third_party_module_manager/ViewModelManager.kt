package com.whynotpot.third_party_module_manager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ViewModelManager @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    fun setLoading(value: Boolean) {
        _isLoading.value = value
    }
    /* fun getLoading(id: String): Flow<Boolean> {
        return authorsFlow.map { authors -> authors.find { it.id == id }!! }
    }*/
/*    val stateFlow = flow {
        val data = savedStateHandle.get("LoadStateFlowKey") ?: repository.loadDataState()
        savedStateHandle.set("LoadStateFlowKey", data)
        emit(data)
    }.stateIn(
        scope = viewModelScope + Dispatchers.IO,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = "Nothing"
    )*/
}

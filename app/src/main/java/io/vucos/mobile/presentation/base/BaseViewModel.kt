package io.vucos.mobile.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    protected fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }


    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }
    protected val areResponsesSuccessful by lazy { mutableListOf<Boolean>() }

    protected var isInitial = false

    protected lateinit var errorText: String

    protected val _uiState = MutableStateFlow(UiState.loadingState())
    val uiState get() = _uiState.asStateFlow()

    protected fun setUiState() {
        _uiState.value = if (areResponsesSuccessful.contains(false)) UiState.errorState(isInitial, errorText) else UiState.successState()
        areResponsesSuccessful.clear()
    }

    fun retryConnection(action: () -> Unit) {
        _uiState.value = UiState.loadingState(isInitial)
        action()
    }
}
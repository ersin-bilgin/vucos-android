package io.vucos.mobile.presentation.screens.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.model.resources.genres.GenresResponse
import io.vucos.mobile.domain.usecase.resources.genres.GetAllGenres
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.TokenManager
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllGenres: GetAllGenres,
    private val tokenManager: TokenManager,
    application: Application
) : BaseViewModel() {
    private val _genres = MutableLiveData<List<GenresResponse>>()
    val genres: LiveData<List<GenresResponse>> = _genres

    private val _isShimmerLoading = MutableLiveData<Boolean>()
    val isShimmerLoading: LiveData<Boolean> = _isShimmerLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchGenres()
    }

    fun fetchGenres() {
        viewModelScope.launch {
            try {
                _isShimmerLoading.value = true
                _error.value = null

                val token = tokenManager.getToken().toString()
                getAllGenres.invoke("Bearer $token").collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            try {
                                _genres.value = result.data.data
                                _isShimmerLoading.value = false
                            } catch (e: Exception) {
                                _error.value = "Veri işlenirken bir hata oluştu: ${e.message}"
                                _isShimmerLoading.value = false
                            }
                        }
                        is Resource.Error -> {
                            _error.value = result.message
                            _isShimmerLoading.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                _error.value = "Veri alınırken bir hata oluştu: ${e.message}"
                _isShimmerLoading.value = false
            }
        }
    }

}
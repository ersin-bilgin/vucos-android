package io.vucos.mobile.presentation.screens.play_movie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PlayMovieViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {
    private val _isPlaying = MutableLiveData<Boolean>()
    val isPlaying: LiveData<Boolean> = _isPlaying

    private val _currentPosition = MutableLiveData<Long>()
    val currentPosition: LiveData<Long> = _currentPosition

    private val _duration = MutableLiveData<Long>()
    val duration: LiveData<Long> = _duration

    fun setPlayingState(isPlaying: Boolean) {
        _isPlaying.value = isPlaying
    }

    fun updatePosition(position: Long) {
        _currentPosition.value = position
    }

    fun updateDuration(duration: Long) {
        _duration.value = duration
    }
}
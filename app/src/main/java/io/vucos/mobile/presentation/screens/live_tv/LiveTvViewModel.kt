package io.vucos.mobile.presentation.screens.live_tv

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LiveTvViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {
}
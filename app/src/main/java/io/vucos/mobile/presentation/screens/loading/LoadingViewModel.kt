package io.vucos.mobile.presentation.screens.loading

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.presentation.base.BaseViewModel
import javax.inject.Inject
@HiltViewModel
class LoadingViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {
}
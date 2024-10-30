package io.vucos.mobile.presentation.screens.for_you

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.usecase.catalog.GetCatalogHome
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.util.TokenManager
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val getCatalogHome: GetCatalogHome,
    private val tokenManager: TokenManager,
    application: Application
) : BaseViewModel() {


}
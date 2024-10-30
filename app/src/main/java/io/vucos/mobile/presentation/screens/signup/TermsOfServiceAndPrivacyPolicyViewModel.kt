package io.vucos.mobile.presentation.screens.signup

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.usecase.application.GetValidate
import io.vucos.mobile.domain.usecase.authentication.GetLogin
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.util.TokenManager
import javax.inject.Inject

@HiltViewModel
class TermsOfServiceAndPrivacyPolicyViewModel @Inject constructor(
    private val getLogin: GetLogin,
    private val tokenManager: TokenManager,
    private val getValidate: GetValidate,
    application: Application
) : BaseViewModel() {
}
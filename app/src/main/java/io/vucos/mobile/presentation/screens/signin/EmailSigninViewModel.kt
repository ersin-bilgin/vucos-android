package io.vucos.mobile.presentation.screens.signin

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.dto.application.validate.ValidateDTO
import io.vucos.mobile.domain.dto.authentication.login.LoginDTO
import io.vucos.mobile.domain.usecase.application.GetValidate
import io.vucos.mobile.domain.usecase.authentication.GetLogin
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.presentation.base.UiState
import io.vucos.mobile.util.DeviceInfoInformations
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.TokenManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailSigninViewModel @Inject constructor(
    private val getLogin: GetLogin,
    private val tokenManager: TokenManager,
    private val getValidate: GetValidate,
    application: Application
) : BaseViewModel() {
    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val deviceInfoUtil = DeviceInfoInformations(application)

    private val _loginSuccess = MutableSharedFlow<Boolean>()
    val loginSuccess = _loginSuccess.asSharedFlow()

    private val _loginEvent = MutableSharedFlow<Unit>()
    val loginEvent = _loginEvent.asSharedFlow()

    private val _isValidateSucceeded = MutableLiveData<Boolean>()
    val isValidateSucceeded: LiveData<Boolean> = _isValidateSucceeded

    private val _errorEvent = MutableSharedFlow<String>()
    val errorEvent = _errorEvent.asSharedFlow()

    init {
        // Set initial state where the button is enabled
        _uiState.value = UiState(isLoading = false, isSuccess = false, isError = false)
    }

    fun login() {
        val validateData = ValidateDTO(
            apiKey = "IT6DorWL1gudb2dRSDzbxMceu6DUpIH9JH+wSZMMDRp79mvlc9pBSzH3FTISxOzv",
            environment = "LIVE",
            appVersion = "0.0.1",
            appBuildNumber = 1,
            deviceId = "23f89b6c-d3c2-4e3d-8bb7-51e0c52ee491"
        )


        val currentEmail = email.value
        val currentPassword = password.value
        val deviceBrand = deviceInfoUtil.getDeviceBrand()
        val model = deviceInfoUtil.getDeviceModel()

        val loginData = LoginDTO(
            username = email.value ?: "",
            password = password.value ?: "",
            deviceBrand = deviceBrand,
            deviceModel = model,
            deviceName = deviceInfoUtil.getDeviceName(),
            operatingSystem = deviceInfoUtil.getOsSystem()
        )

        if (currentEmail.isNullOrEmpty() || currentPassword.isNullOrEmpty()) {
            _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Email or password cannot be empty")
            viewModelScope.launch {
                _errorEvent.emit("Email or password cannot be empty")
            }
            return
        }

        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true, isSuccess = false, isError = false)
            try {
                getValidate(validateData).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            val responseValidate = result.data
                            _isValidateSucceeded.value = responseValidate.isSucceeded
                            if(responseValidate.isSucceeded){
                                Log.d("LoginViewModel", "Navigating to LoadingFragment")
                                val token = responseValidate.data.token;
                                getLogin("Bearer ${token}", loginData).collect { loginResult ->
                                    when (loginResult) {
                                        is Resource.Success -> {
                                            val loginToken = loginResult.data.data.token
                                            tokenManager.saveToken(loginToken)
                                            _uiState.value = UiState(isLoading = false, isSuccess = true, isError = false)
                                            _loginEvent.emit(Unit)
                                            _loginSuccess.emit(true)
                                        }
                                        is Resource.Error -> {
                                            _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                                            _errorEvent.emit("Email or password cannot be empty")
                                            _loginSuccess.emit(false)
                                        }
                                        else -> {
                                            _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                                            _errorEvent.emit("Email or password cannot be empty")
                                            Log.d("responseLogin", "Unexpected result type: ${result::class.java}")
                                            _loginSuccess.emit(false)
                                        }
                                    }
                                }
                            }else{
                                _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                                _errorEvent.emit("Email or password cannot be empty")
                                _loginSuccess.emit(false)
                            }
                            // ... (Success durumu için olan kodlar)
                        }
                        is Resource.Error -> {
                            _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                            _errorEvent.emit("Email or password cannot be empty")
                            _loginSuccess.emit(false)
                        }
                        else -> {
                            Log.d("ValidateResponse", "Unexpected result type: ${result::class.java}")
                            _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                            _errorEvent.emit("Email or password cannot be empty")
                            _loginSuccess.emit(false)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("LoginError", "Error during login", e)
                _uiState.value = UiState(isLoading = false, isSuccess = false, isError = true, errorText = "Invalid credentials")
                _loginSuccess.emit(false)
            }
        }
    }
}
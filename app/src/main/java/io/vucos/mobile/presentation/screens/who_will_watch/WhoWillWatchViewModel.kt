package io.vucos.mobile.presentation.screens.who_will_watch

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.dto.authentication.select_profile.SelectProfileDTO
import io.vucos.mobile.domain.model.profiles.Profile
import io.vucos.mobile.domain.usecase.authentication.SelectProfile
import io.vucos.mobile.domain.usecase.profiles.GetProfiles
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.presentation.navigation_events.HomeMainNavigationEvent
import io.vucos.mobile.util.Constants
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.TokenManager
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class WhoWillWatchViewModel @Inject constructor(
    private val getProfiles: GetProfiles,
    private val selectProfile: SelectProfile,
    private val tokenManager: TokenManager,
    application: Application
) : BaseViewModel() {

    private val _isDataReady = MutableLiveData<Boolean>()
    val isDataReady: LiveData<Boolean> = _isDataReady

    private val _profiles = MutableLiveData<List<Profile>>()
    val profiles: LiveData<List<Profile>> = _profiles


    private val _navigationEvent = MutableLiveData<HomeMainNavigationEvent>()
    val navigationEvent: LiveData<HomeMainNavigationEvent> = _navigationEvent


    fun loadData() {
        viewModelScope.launch {
            setLoading(true)
            _isDataReady.value = false
            val token = tokenManager.getToken().toString()
            getProfiles("Bearer $token").collect { profilesResult ->
                when (profilesResult) {
                    is Resource.Success -> {
                        val profileList = profilesResult.data.data.profiles
                        _profiles.value = profileList
                        _isDataReady.value = true
                    }
                    is Resource.Error -> {
                        _isDataReady.value = false
                    }
                    else -> {
                        _isDataReady.value = false
                    }
                }
                setLoading(false)
            }
        }
    }

    fun onProfileSelected(profile: Profile) {
        _isLoading.value = true
        val selectProfileData = SelectProfileDTO(
            profileUid = profile.uId,
            isChildProfile = profile.isChildProfile,
            deviceToken = Constants.Default.ONESIGNAL_APP_ID
        )

        viewModelScope.launch {
            val token = tokenManager.getToken().toString()
            selectProfile("Bearer ${token}",selectProfileData).collect { profilesResult ->
                when (profilesResult) {
                    is Resource.Success -> {
                        val getSelectProfileToken = profilesResult.data.data.token
                        if(!getSelectProfileToken.isNullOrEmpty()){
                            tokenManager.clearToken()
                            tokenManager.saveToken(getSelectProfileToken)


                            _navigationEvent.value = HomeMainNavigationEvent.NavigateToMainContent

                            _isDataReady.value = false

                        }else{

                            _isLoading.value = false
                        }
                    }
                    is Resource.Error -> {
                        _isDataReady.value = false
                    }
                    else -> {
                        _isDataReady.value = false
                    }
                }
            }
        }
    }
}
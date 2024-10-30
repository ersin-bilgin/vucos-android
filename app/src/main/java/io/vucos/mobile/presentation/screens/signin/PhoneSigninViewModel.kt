package io.vucos.mobile.presentation.screens.signin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.presentation.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class PhoneSigninViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {
    val phoneNumber = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        val currentPhoneNumber = phoneNumber.value

        if (currentPhoneNumber.isNullOrEmpty()) {
            // Hata durumu: Telefon numarası boş
            // Burada bir hata mesajı gösterebilirsiniz
            return
        }

        // Telefon numarası ile giriş işlemini gerçekleştir
    }
}
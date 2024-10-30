package io.vucos.mobile.presentation.screens.signup

import android.app.Application
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    application: Application
) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        val currentEmail = email.value
        val currentPassword = password.value

        if (currentEmail.isNullOrEmpty() || currentPassword.isNullOrEmpty()) {
            // Hata durumu: Email veya şifre boş
            // Burada bir hata mesajı gösterebilirsiniz
            return
        }

        // Burada giriş işlemini gerçekleştirebilirsiniz
        // Örneğin:
        // loginUseCase.execute(currentEmail, currentPassword)
        //     .subscribeOn(Schedulers.io())
        //     .observeOn(AndroidSchedulers.mainThread())
        //     .subscribe({ result ->
        //         // Başarılı giriş işlemi
        //     }, { error ->
        //         // Hata durumu
        //     })
    }
}
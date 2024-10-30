package io.vucos.mobile.data.repository.authentication

import io.vucos.mobile.data.api.authentication.AuthenticationApi
import io.vucos.mobile.domain.dto.authentication.login.LoginDTO
import io.vucos.mobile.domain.dto.authentication.select_profile.SelectProfileDTO
import io.vucos.mobile.domain.model.authentication.login.Login
import io.vucos.mobile.domain.model.authentication.select_profile.SelectProfileResponse
import io.vucos.mobile.domain.repository.authentication.IAuthenticationRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepository @Inject constructor(private val api: AuthenticationApi,
                                                   private val safeApiCall: SafeApiCall
) : IAuthenticationRepository {
    override suspend fun Login(apiKey:String, login: LoginDTO): Resource<Login> = safeApiCall.execute {
        api.getLogin(apiKey, login)
    }

    override suspend fun SelectProfile(apiKey:String, SelectProfile: SelectProfileDTO): Resource<SelectProfileResponse> = safeApiCall.execute {
        api.getSelectProfile(apiKey, SelectProfile)
    }

}
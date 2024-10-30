package io.vucos.mobile.domain.repository.authentication

import io.vucos.mobile.domain.dto.authentication.login.LoginDTO
import io.vucos.mobile.domain.dto.authentication.select_profile.SelectProfileDTO
import io.vucos.mobile.domain.model.authentication.login.Login
import io.vucos.mobile.domain.model.authentication.select_profile.SelectProfileResponse
import io.vucos.mobile.util.Resource

interface IAuthenticationRepository {
    suspend fun Login(apiKey: String,login: LoginDTO): Resource<Login>

    suspend fun SelectProfile(apiKey: String,selectProfile: SelectProfileDTO): Resource<SelectProfileResponse>

}
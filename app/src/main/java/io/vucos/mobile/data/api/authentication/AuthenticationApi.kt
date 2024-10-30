package io.vucos.mobile.data.api.authentication

import io.vucos.mobile.domain.dto.authentication.login.LoginDTO
import io.vucos.mobile.domain.dto.authentication.select_profile.SelectProfileDTO
import io.vucos.mobile.domain.model.authentication.login.Login
import io.vucos.mobile.domain.model.authentication.select_profile.SelectProfileResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("api/auth/login")
    suspend fun getLogin(@Header("Authorization") apiKey: String, @Body body: LoginDTO): Login

    @POST("api/auth/select-profile")
    suspend fun getSelectProfile(@Header("Authorization") apiKey: String, @Body body: SelectProfileDTO): SelectProfileResponse
}
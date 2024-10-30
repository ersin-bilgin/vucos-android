package io.vucos.mobile.data.api.profiles

import io.vucos.mobile.domain.model.profiles.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfilesApi {
    @GET("api/profiles")
    suspend fun getProfiles(@Header("Authorization") apiKey: String): ProfileResponse
}
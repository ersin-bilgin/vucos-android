package io.vucos.mobile.data.repository.profiles

import io.vucos.mobile.data.api.profiles.ProfilesApi
import io.vucos.mobile.domain.model.profiles.ProfileResponse
import io.vucos.mobile.domain.repository.profiles.IProfilesRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilesRepository @Inject constructor(private val api: ProfilesApi, private val safeApiCall: SafeApiCall) :
    IProfilesRepository {
    override suspend fun GetProfiles(apiKey:String): Resource<ProfileResponse> = safeApiCall.execute {
        api.getProfiles(apiKey)
    }
}

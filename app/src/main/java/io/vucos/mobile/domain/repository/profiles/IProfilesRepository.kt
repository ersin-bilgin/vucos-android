package io.vucos.mobile.domain.repository.profiles

import io.vucos.mobile.domain.model.profiles.ProfileResponse
import io.vucos.mobile.util.Resource

interface IProfilesRepository {
    suspend fun GetProfiles(apiKey: String): Resource<ProfileResponse>
}
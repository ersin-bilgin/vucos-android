package io.vucos.mobile.domain.usecase.profiles

import io.vucos.mobile.domain.model.profiles.ProfileResponse
import io.vucos.mobile.domain.repository.profiles.IProfilesRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProfiles @Inject constructor(private val ProfilesRepository: IProfilesRepository)
{
    operator fun invoke(apiKey:String) : Flow<Resource<ProfileResponse>> = flow {
        emit(
            ProfilesRepository.GetProfiles(apiKey)
        )
    }
}
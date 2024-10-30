package io.vucos.mobile.domain.usecase.authentication

import io.vucos.mobile.domain.dto.authentication.select_profile.SelectProfileDTO
import io.vucos.mobile.domain.model.authentication.select_profile.SelectProfileResponse
import io.vucos.mobile.domain.repository.authentication.IAuthenticationRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SelectProfile @Inject constructor(private val authenticationRepository: IAuthenticationRepository)
{
    operator fun invoke(apiKey:String, selectProfile: SelectProfileDTO) : Flow<Resource<SelectProfileResponse>> = flow {
        emit(
            authenticationRepository.SelectProfile(apiKey,selectProfile)
        )
    }
}
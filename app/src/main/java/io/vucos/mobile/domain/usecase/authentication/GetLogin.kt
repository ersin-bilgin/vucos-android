package io.vucos.mobile.domain.usecase.authentication

import io.vucos.mobile.domain.dto.authentication.login.LoginDTO
import io.vucos.mobile.domain.model.authentication.login.Login
import io.vucos.mobile.domain.repository.authentication.IAuthenticationRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLogin @Inject constructor(private val authenticationRepository: IAuthenticationRepository)
{
    operator fun invoke(apiKey:String, login: LoginDTO) : Flow<Resource<Login>> = flow {
        emit(
            authenticationRepository.Login(apiKey,login)
        )
    }
}
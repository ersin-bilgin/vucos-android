package io.vucos.mobile.domain.usecase.application

import io.vucos.mobile.domain.dto.application.validate.ValidateDTO
import io.vucos.mobile.domain.model.application.validate.Validate
import io.vucos.mobile.domain.repository.application.IApplicationRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetValidate @Inject constructor(private val applicationRepository: IApplicationRepository)
{
    operator fun invoke(validate: ValidateDTO): Flow<Resource<Validate>> = flow { emit(
        applicationRepository.Validate(validate))
    }
}
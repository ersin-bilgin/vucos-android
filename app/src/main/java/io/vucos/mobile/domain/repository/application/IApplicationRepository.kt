package io.vucos.mobile.domain.repository.application

import io.vucos.mobile.domain.dto.application.validate.ValidateDTO
import io.vucos.mobile.domain.model.application.validate.Validate
import io.vucos.mobile.util.Resource

interface IApplicationRepository {
    suspend fun Validate(validate: ValidateDTO): Resource<Validate>
}
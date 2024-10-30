package io.vucos.mobile.data.repository.application

import io.vucos.mobile.data.api.application.ApplicationApi
import io.vucos.mobile.domain.dto.application.validate.ValidateDTO
import io.vucos.mobile.domain.model.application.validate.Validate
import io.vucos.mobile.domain.repository.application.IApplicationRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplicationRepository @Inject constructor(private val api: ApplicationApi,
                                                private val safeApiCall: SafeApiCall
) : IApplicationRepository {
    override suspend fun Validate(validate: ValidateDTO): Resource<Validate> = safeApiCall.execute  {
        api.getValidate(validate)
    }
}
package io.vucos.mobile.data.api.application

import io.vucos.mobile.domain.dto.application.validate.ValidateDTO
import io.vucos.mobile.domain.model.application.validate.Validate
import retrofit2.http.Body
import retrofit2.http.POST

interface ApplicationApi {
    @POST("api/app/validate")
    suspend fun getValidate(@Body body: ValidateDTO): Validate
}
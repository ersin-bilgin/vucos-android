package io.vucos.mobile.domain.dto.application.validate

import com.google.gson.annotations.SerializedName

data class ValidateDTO(
    @SerializedName("apiKey")
    val apiKey: String,

    @SerializedName("environment")
    val environment: String,

    @SerializedName("appVersion")
    val appVersion: String,

    @SerializedName("appBuildNumber")
    val appBuildNumber: Int,

    @SerializedName("deviceId")
    val deviceId: String
)
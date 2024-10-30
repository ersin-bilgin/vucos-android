package io.vucos.mobile.domain.model.application.validate

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Token")
    val token: String,
    @SerializedName("DeviceId")
    val deviceId: String,
    @SerializedName("NewVersionAvailable")
    val newVersionAvailable: Boolean,
    @SerializedName("NewVersionInfo")
    val newVersionInfo: NewVersionInfo
)
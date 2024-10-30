package io.vucos.mobile.domain.dto.authentication.login

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("Username") val username: String,
    @SerializedName("Password") val password: String,
    @SerializedName("DeviceBrand") val deviceBrand: String,
    @SerializedName("DeviceModel") val deviceModel: String,
    @SerializedName("DeviceName") val deviceName: String,
    @SerializedName("OperatingSystem") val operatingSystem: String
)
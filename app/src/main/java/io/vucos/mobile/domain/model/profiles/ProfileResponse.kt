package io.vucos.mobile.domain.model.profiles

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("IsSucceeded") val isSucceeded: Boolean,
    @SerializedName("ResultCode") val resultCode: Int,
    @SerializedName("Label") val label: String,
    @SerializedName("Message") val message: String,
    @SerializedName("Description") val description: String,
    @SerializedName("Data") val data: ProfileData
)
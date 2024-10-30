package io.vucos.mobile.domain.dto.authentication.select_profile

import com.google.gson.annotations.SerializedName

data class SelectProfileDTO (
    @SerializedName("ProfileUId") val profileUid: String,
    @SerializedName("IsChildProfile") val isChildProfile: Boolean,
    @SerializedName("DeviceToken") val deviceToken: String,
)
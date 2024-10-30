package io.vucos.mobile.domain.model.profiles

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("AllowNewProfile") val allowNewProfile: Boolean,
    @SerializedName("MaxAllowedProfileCount") val maxAllowedProfileCount: Int,
    @SerializedName("Profiles") val profiles: List<Profile>,
    @SerializedName("AllowSelectProfile") val allowSelectProfile: Boolean
)
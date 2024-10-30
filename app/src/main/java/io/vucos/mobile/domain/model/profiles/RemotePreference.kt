package io.vucos.mobile.domain.model.profiles

import com.google.gson.annotations.SerializedName

data class RemotePreference(
    @SerializedName("RemoteTypeId") val remoteTypeId: Int,
    @SerializedName("RemoteTypeName") val remoteTypeName: String,
    @SerializedName("Enabled") val enabled: Boolean
)
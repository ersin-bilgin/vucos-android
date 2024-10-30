package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class DeviceAvailability(
    @SerializedName("DeviceTypeName") val deviceTypeName: String,
    @SerializedName("IsAvailable") val isAvailable: Boolean,
    @SerializedName("IsWatchAvailable") val isWatchAvailable: Boolean
)
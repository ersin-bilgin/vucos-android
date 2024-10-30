package io.vucos.mobile.domain.model.authentication.select_profile

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Token") val token: String,
    @SerializedName("StartChannel") val startChannel: Boolean,
    @SerializedName("ChannelUId") val channelUId: String?
)
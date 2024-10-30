package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("UId") val uId: String,
    @SerializedName("Name") val name: String,
    @SerializedName("Type") val type: String
)
package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("UId") val uId: String,
    @SerializedName("Name") val name: String
)
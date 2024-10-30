package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class AudioTrack(
    @SerializedName("Code") val code: String,
    @SerializedName("Label") val label: String
)
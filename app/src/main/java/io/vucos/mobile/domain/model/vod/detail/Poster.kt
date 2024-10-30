package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("ImageUrl") val imageUrl: String,
    @SerializedName("Type") val type: String
)
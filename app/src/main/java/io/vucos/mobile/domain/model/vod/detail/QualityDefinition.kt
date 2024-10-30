package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class QualityDefinition(
    @SerializedName("Name") val name: String,
    @SerializedName("LogoUrl") val logoUrl: String
)
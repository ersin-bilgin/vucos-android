package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class VodDetail(
    @SerializedName("IsSucceeded") val isSucceeded: Boolean,
    @SerializedName("ResultCode") val resultCode: Int,
    @SerializedName("Label") val label: String,
    @SerializedName("Message") val message: String,
    @SerializedName("Description") val description: String,
    @SerializedName("Data") val data: List<VodContentItem>
)
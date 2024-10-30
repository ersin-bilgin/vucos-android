package io.vucos.mobile.domain.model.application.validate

import com.google.gson.annotations.SerializedName

data class NewVersionInfo(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Version")
    val version: String,
    @SerializedName("BuildNumber")
    val buildNumber: Int,
    @SerializedName("ForceUpdate")
    val forceUpdate: Boolean
)
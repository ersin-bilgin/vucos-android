package io.vucos.mobile.domain.model.authentication.login

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Token")
    val token: String,
    @SerializedName("CatalogPreferencesSelected")
    val catalogPreferencesSelected: Boolean,
    @SerializedName("AllowSelectProfile")
    val allowSelectProfile: Boolean,
    @SerializedName("StartLiveChannel")
    val startLiveChannel: Boolean,
    @SerializedName("StartupChannelUId")
    val startupChannelUId: String?
)
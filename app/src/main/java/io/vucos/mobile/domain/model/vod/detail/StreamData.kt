package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class StreamData(
    @SerializedName("IsDrmEnabled") val isDrmEnabled: Boolean,
    @SerializedName("IsExtraProtectionEnabled") val isExtraProtectionEnabled: Boolean,
    @SerializedName("DrmAuthKey") val drmAuthKey: String,
    @SerializedName("DrmFairPlayAuthKey") val drmFairPlayAuthKey: String,
    @SerializedName("WidevineServerUrl") val widevineServerUrl: String,
    @SerializedName("PlayReadyServerUrl") val playReadyServerUrl: String,
    @SerializedName("FairPlayServerUrl") val fairPlayServerUrl: String,
    @SerializedName("FairPlayCertificateUrl") val fairPlayCertificateUrl: String,
    @SerializedName("DefaultStreamUrl") val defaultStreamUrl: String,
    @SerializedName("HlsStreamUrl") val hlsStreamUrl: String,
    @SerializedName("DashStreamUrl") val dashStreamUrl: String
)
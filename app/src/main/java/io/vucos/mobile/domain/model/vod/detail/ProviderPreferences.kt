package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class ProviderPreferences(
    @SerializedName("ShowPosterInDetails") val showPosterInDetails: Boolean,
    @SerializedName("ShowParentalControlIconsInDetails") val showParentalControlIconsInDetails: Boolean,
    @SerializedName("ShowTrailersInDetails") val showTrailersInDetails: Boolean,
    @SerializedName("ShowCompletionPercentInDetails") val showCompletionPercentInDetails: Boolean,
    @SerializedName("ShowReleaseYearInDetails") val showReleaseYearInDetails: Boolean,
    @SerializedName("ShowActorsInDetails") val showActorsInDetails: Boolean,
    @SerializedName("ShowDirectorsInDetails") val showDirectorsInDetails: Boolean,
    @SerializedName("ShowLicenseInDetails") val showLicenseInDetails: Boolean,
    @SerializedName("ShowRatingsInDetails") val showRatingsInDetails: Boolean,
    @SerializedName("ShowSeriesInfoInDetails") val showSeriesInfoInDetails: Boolean,
    @SerializedName("ShowAvailablePlatforms") val showAvailablePlatforms: Boolean
)
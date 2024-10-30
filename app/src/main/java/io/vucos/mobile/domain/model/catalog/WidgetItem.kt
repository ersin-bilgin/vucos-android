package io.vucos.mobile.domain.model.catalog

import com.google.gson.annotations.SerializedName

data class WidgetItem(
    @SerializedName("ChannelUId") val channelUId: String?,
    @SerializedName("ChannelName") val channelName: String?,
    @SerializedName("ChannelPrimaryLogoImageUrl") val channelPrimaryLogoImageUrl: String?,
    @SerializedName("ChannelSecondaryLogoImageUrl") val channelSecondaryLogoImageUrl: String?,
    @SerializedName("HasEpg") val hasEpg: Boolean?,
    @SerializedName("EpgTitle") val epgTitle: String?,
    @SerializedName("EpgImageUrl") val epgImageUrl: String?,
    @SerializedName("EpgStartDateTime") val epgStartDateTime: String?,
    @SerializedName("EpgEndDateTime") val epgEndDateTime: String?,
    @SerializedName("EpgPercent") val epgPercent: Double?,
    @SerializedName("EpgBroadcastType") val epgBroadcastType: String?,
    @SerializedName("DisplayOrder") val displayOrder: Int,
    @SerializedName("IsInFavorites") val isInFavorites: Boolean,
    @SerializedName("ContentUId") val contentUId: String?,
    @SerializedName("Title") val title: String?,
    @SerializedName("PosterImageUrl") val posterImageUrl: String?,
    @SerializedName("IsInWatchHistory") val isInWatchHistory: Boolean?,
    @SerializedName("PauseTime") val pauseTime: Int?,
    @SerializedName("Duration") val duration: Int,
    @SerializedName("WatchedPercent") val watchedPercent: Double,
    @SerializedName("LastUpdated") val lastUpdated: String?
)
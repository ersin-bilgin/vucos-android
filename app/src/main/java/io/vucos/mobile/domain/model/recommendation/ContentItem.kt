package io.vucos.mobile.domain.model.recommendation

import com.google.gson.annotations.SerializedName

data class ContentItem(
    @SerializedName("ContentUId") val contentUId: String,
    @SerializedName("Title") val title: String,
    @SerializedName("PosterImageUrl") val posterImageUrl: String,
    @SerializedName("Duration") val duration: Int,
    @SerializedName("ReleaseYear") val releaseYear: Int,
    @SerializedName("Type") val type: String,
    @SerializedName("EpisodeNumber") val episodeNumber: Int,
    @SerializedName("SeasonNumber") val seasonNumber: Int,
    @SerializedName("IsInFavorites") val isInFavorites: Boolean,
    @SerializedName("IsInDefaultPlaylist") val isInDefaultPlaylist: Boolean,
    @SerializedName("IsInWatchHistory") val isInWatchHistory: Boolean,
    @SerializedName("DisplayOrder") val displayOrder: Int
)
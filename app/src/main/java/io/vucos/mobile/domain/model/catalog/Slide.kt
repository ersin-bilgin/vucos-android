package io.vucos.mobile.domain.model.catalog

import com.google.gson.annotations.SerializedName

data class Slide(
    @SerializedName("Name") val name: String,
    @SerializedName("MobileImageUrl") val mobileImageUrl: String,
    @SerializedName("WebImageUrl") val webImageUrl: String,
    @SerializedName("LargeImageUrl") val largeImageUrl: String,
    @SerializedName("VideoUrl") val videoUrl: String,
    @SerializedName("IsChild") val isChild: Boolean,
    @SerializedName("ContentType") val contentType: String,
    @SerializedName("ContentUId") val contentUId: String,
    @SerializedName("IsInPlaylist") val isInPlaylist: Boolean,
    @SerializedName("IsInFavorites") val isInFavorites: Boolean,
    @SerializedName("Genres") val genres: List<String>,
    @SerializedName("Ratings") val ratings: List<String>
)
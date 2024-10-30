package io.vucos.mobile.domain.model.vod.detail

import com.google.gson.annotations.SerializedName

data class VodContentItem(
    @SerializedName("UId") val uId: String,
    @SerializedName("Title") val title: String,
    @SerializedName("OriginalTitle") val originalTitle: String,
    @SerializedName("Description") val description: String,
    @SerializedName("OriginalDescription") val originalDescription: String,
    @SerializedName("ParentTitle") val parentTitle: String,
    @SerializedName("Duration") val duration: Int,
    @SerializedName("ReleaseYear") val releaseYear: Int,
    @SerializedName("Type") val type: String,
    @SerializedName("ShowAsComingSoon") val showAsComingSoon: Boolean,
    @SerializedName("LicenseStart") val licenseStart: String,
    @SerializedName("LicenseEnd") val licenseEnd: String,
    @SerializedName("EpisodeNumber") val episodeNumber: Int,
    @SerializedName("SeasonNumber") val seasonNumber: Int,
    @SerializedName("IsGeneralAudience") val isGeneralAudience: Boolean,
    @SerializedName("IsPlus7Audience") val isPlus7Audience: Boolean,
    @SerializedName("IsPlus13Audience") val isPlus13Audience: Boolean,
    @SerializedName("IsPlus18Audience") val isPlus18Audience: Boolean,
    @SerializedName("IsViolenceAndFearAudience") val isViolenceAndFearAudience: Boolean,
    @SerializedName("IsAdultAudience") val isAdultAudience: Boolean,
    @SerializedName("IsNegativityAudience") val isNegativityAudience: Boolean,
    @SerializedName("RequirePinCode") val requirePinCode: Boolean,
    @SerializedName("IsSelectedEpisode") val isSelectedEpisode: Boolean,
    @SerializedName("PausedTime") val pausedTime: Int,
    @SerializedName("PreferredAudioTrack") val preferredAudioTrack: String,
    @SerializedName("PreferredTextTrack") val preferredTextTrack: String,
    @SerializedName("IsInFavorites") val isInFavorites: Boolean,
    @SerializedName("ReactionValue") val reactionValue: Int,
    @SerializedName("IsInWatchHistory") val isInWatchHistory: Boolean,
    @SerializedName("IsInDefaultPlaylist") val isInDefaultPlaylist: Boolean,
    @SerializedName("IsOfferAvailableToWatch") val isOfferAvailableToWatch: Boolean,
    @SerializedName("IsAvailableToWatch") val isAvailableToWatch: Boolean,
    @SerializedName("IsAvailableToBuy") val isAvailableToBuy: Boolean,
    @SerializedName("IsAvailableToRent") val isAvailableToRent: Boolean,
    @SerializedName("MinimumOfferName") val minimumOfferName: String,
    @SerializedName("IsNpvrRecord") val isNpvrRecord: Boolean,
    @SerializedName("UserRating") val userRating: Double,
    @SerializedName("UserRatingAverage") val userRatingAverage: Double,
    @SerializedName("ReactionsAverage") val reactionsAverage: Double,
    @SerializedName("AverageLikes") val averageLikes: Double,
    @SerializedName("AverageDislikes") val averageDislikes: Double,
    @SerializedName("TrailerStartSecond") val trailerStartSecond: Int,
    @SerializedName("TrailerEndSecond") val trailerEndSecond: Int,
    @SerializedName("IsRented") val isRented: Boolean,
    @SerializedName("RentStarted") val rentStarted: Boolean,
    @SerializedName("RentExpired") val rentExpired: Boolean,
    @SerializedName("RentStartDate") val rentStartDate: String,
    @SerializedName("RentEndDate") val rentEndDate: String,
    @SerializedName("DemoSubscriberLimitMessage") val demoSubscriberLimitMessage: String,
    @SerializedName("IsAdult") val isAdult: Boolean,
    @SerializedName("StreamData") val streamData: StreamData,
    @SerializedName("ProviderPreferences") val providerPreferences: ProviderPreferences,
    @SerializedName("Posters") val posters: List<Poster>,
    @SerializedName("AudioTracks") val audioTracks: List<AudioTrack>,
    @SerializedName("TextTracks") val textTracks: List<Any>,
    @SerializedName("Bookmarks") val bookmarks: List<Any>,
    @SerializedName("Trailers") val trailers: List<Any>,
    @SerializedName("Genres") val genres: List<Genres>,
    @SerializedName("Cast") val cast: List<Cast>,
    @SerializedName("Keywords") val keywords: List<Any>,
    @SerializedName("Categories") val categories: List<Any>,
    @SerializedName("Bundles") val bundles: List<Any>,
    @SerializedName("QualityDefinitions") val qualityDefinitions: List<QualityDefinition>,
    @SerializedName("Ratings") val ratings: List<Any>,
    @SerializedName("Playlists") val playlists: List<Any>,
    @SerializedName("PurchaseDetails") val purchaseDetails: List<Any>,
    @SerializedName("RentDetails") val rentDetails: List<Any>,
    @SerializedName("DeviceAvailability") val deviceAvailability: List<DeviceAvailability>
)
package io.vucos.mobile.domain.model.profiles

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("UId") val uId: String,
    @SerializedName("Name") val name: String,
    @SerializedName("AvatarUId") val avatarUId: String,
    @SerializedName("IsChildProfile") val isChildProfile: Boolean,
    @SerializedName("AvatarImageUrl") val avatarImageUrl: String,
    @SerializedName("IsDefault") val isDefault: Boolean,
    @SerializedName("LanguageCode") val languageCode: String,
    @SerializedName("PinEnabled") val pinEnabled: Boolean,
    @SerializedName("PasswordEnabled") val passwordEnabled: Boolean,
    @SerializedName("StartChannel") val startChannel: Boolean,
    @SerializedName("ChannelUId") val channelUId: String?,
    @SerializedName("AutoPlayNextContent") val autoPlayNextContent: Boolean,
    @SerializedName("AutoPlayPreviews") val autoPlayPreviews: Boolean,
    @SerializedName("OnlyShowWatchedItems") val onlyShowWatchedItems: Boolean,
    @SerializedName("OnlyShowNotWatchedItems") val onlyShowNotWatchedItems: Boolean,
    @SerializedName("OnlyShowClaimedItems") val onlyShowClaimedItems: Boolean,
    @SerializedName("OnlyShowFavoriteChannels") val onlyShowFavoriteChannels: Boolean,
    @SerializedName("SelectedThemeUId") val selectedThemeUId: String,
    @SerializedName("EnableRandomTheme") val enableRandomTheme: Boolean,
    @SerializedName("IsForcedTheme") val isForcedTheme: Boolean,
    @SerializedName("ForceThemeFrom") val forceThemeFrom: String,
    @SerializedName("ForceThemeUntil") val forceThemeUntil: String,
    @SerializedName("LogoImageUrl1") val logoImageUrl1: String,
    @SerializedName("LogoImageUrl2") val logoImageUrl2: String,
    @SerializedName("LogoImageUrl3") val logoImageUrl3: String,
    @SerializedName("LogoImageUrl4") val logoImageUrl4: String,
    @SerializedName("BirthdayMessageModel") val birthdayMessageModel: Any?, // Hala Any? olarak bırakıyoruz çünkü türü belirsiz
    @SerializedName("RemotePreferences") val remotePreferences: List<RemotePreference>
)
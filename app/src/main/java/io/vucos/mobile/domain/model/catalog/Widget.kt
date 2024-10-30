package io.vucos.mobile.domain.model.catalog

import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("Items") val items: List<WidgetItem>,
    @SerializedName("Title") val title: String,
    @SerializedName("WidgetType") val widgetType: String,
    @SerializedName("DisplayOrder") val displayOrder: Int,
    @SerializedName("TargetType") val targetType: String,
    @SerializedName("TargetUId") val targetUId: String,
    @SerializedName("ProfileId") val profileId: Int
)
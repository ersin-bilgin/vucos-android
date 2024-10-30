package io.vucos.mobile.domain.model.catalog

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("Slides") val slides: List<Slide>,
    @SerializedName("Widgets") val widgets: List<Widget>
)
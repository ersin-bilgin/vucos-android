package io.vucos.mobile.domain.model.resources.genres

import com.google.gson.annotations.SerializedName

data class GenresResponse (
    @SerializedName("UId")
    val uid: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("IconImageUrl")
    val iconImageUrl: String
)
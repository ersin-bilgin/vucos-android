package io.vucos.mobile.domain.model.authentication.login

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("IsSucceeded")
    val isSucceeded: Boolean,
    @SerializedName("ResultCode")
    val resultCode: Int,
    @SerializedName("Label")
    val label: String,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("Data")
    val data: Data
)
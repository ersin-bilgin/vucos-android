package io.vucos.mobile.data.api.vod

import io.vucos.mobile.domain.model.vod.detail.VodDetail
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface VodApi {
    @GET("api/vod/detail")
    suspend fun getVodDetail(@Header("Authorization") apiKey: String, @Query("VodUId") vodUId: String?): VodDetail
}
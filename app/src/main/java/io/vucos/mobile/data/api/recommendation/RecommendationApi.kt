package io.vucos.mobile.data.api.recommendation

import io.vucos.mobile.domain.model.recommendation.Recommendation
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RecommendationApi {
    @GET("api/recommendation/for-user")
    suspend fun getForUser(@Header("Authorization") apiKey: String, @Query("VodUId") vodUId: String?): Recommendation

    @GET("api/recommendation/for-vod")
    suspend fun getForVod(@Header("Authorization") apiKey: String, @Query("VodUId") vodUId: String?): Recommendation
}
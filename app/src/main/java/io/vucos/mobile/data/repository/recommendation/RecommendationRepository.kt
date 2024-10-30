package io.vucos.mobile.data.repository.recommendation

import io.vucos.mobile.data.api.recommendation.RecommendationApi
import io.vucos.mobile.domain.model.recommendation.Recommendation
import io.vucos.mobile.domain.repository.recommendation.IRecommendationRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecommendationRepository @Inject constructor(private val api: RecommendationApi, private val safeApiCall: SafeApiCall) :
    IRecommendationRepository {
    override suspend fun GetForUser(apiKey: String, vodUId: String?): Resource<Recommendation> = safeApiCall.execute {
        api.getForUser(apiKey,vodUId)
    }

    override suspend fun GetForVod(apiKey: String, vodUId: String?): Resource<Recommendation> = safeApiCall.execute{
        api.getForVod(apiKey,vodUId)
    }

}
package io.vucos.mobile.domain.repository.recommendation

import io.vucos.mobile.domain.model.recommendation.Recommendation
import io.vucos.mobile.util.Resource

interface IRecommendationRepository {
    suspend fun GetForUser(apiKey: String, vodUId: String?): Resource<Recommendation>
    suspend fun GetForVod(apiKey: String, vodUId: String?): Resource<Recommendation>
}
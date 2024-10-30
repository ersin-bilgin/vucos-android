package io.vucos.mobile.domain.usecase.recommendation

import io.vucos.mobile.domain.model.recommendation.Recommendation
import io.vucos.mobile.domain.repository.recommendation.IRecommendationRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetForYou @Inject constructor(private val RecommendationRepository: IRecommendationRepository)
{
    operator fun invoke(apiKey:String, vodUId:String?) : Flow<Resource<Recommendation>> = flow {
        emit(
            RecommendationRepository.GetForUser(apiKey,vodUId)
        )
    }
}
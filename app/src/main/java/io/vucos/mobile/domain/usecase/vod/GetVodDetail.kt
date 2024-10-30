package io.vucos.mobile.domain.usecase.vod

import io.vucos.mobile.domain.model.vod.detail.VodDetail
import io.vucos.mobile.domain.repository.vod.IVodRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVodDetail @Inject constructor(private val VodRepository: IVodRepository)
{
    operator fun invoke(apiKey:String, vodUId: String?) : Flow<Resource<VodDetail>> = flow {
        emit(
            VodRepository.GetVodDetails(apiKey,vodUId)
        )
    }
}
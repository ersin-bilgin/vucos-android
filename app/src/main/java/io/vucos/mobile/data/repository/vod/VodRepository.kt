package io.vucos.mobile.data.repository.vod

import io.vucos.mobile.data.api.vod.VodApi
import io.vucos.mobile.domain.model.vod.detail.VodDetail
import io.vucos.mobile.domain.repository.vod.IVodRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VodRepository @Inject constructor(private val api: VodApi, private val safeApiCall: SafeApiCall) :
    IVodRepository {
    override suspend fun GetVodDetails(apiKey: String, vodUId: String?): Resource<VodDetail> = safeApiCall.execute {
        api.getVodDetail(apiKey,vodUId)
    }
}
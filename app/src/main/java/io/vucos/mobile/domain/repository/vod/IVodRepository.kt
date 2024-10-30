package io.vucos.mobile.domain.repository.vod

import io.vucos.mobile.domain.model.vod.detail.VodDetail
import io.vucos.mobile.util.Resource

interface IVodRepository {
    suspend fun GetVodDetails(apiKey: String, vodUId: String?): Resource<VodDetail>
}
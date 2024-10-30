package io.vucos.mobile.data.repository.catalog

import io.vucos.mobile.data.api.catalog.CatalogApi
import io.vucos.mobile.domain.model.catalog.CatalogHomeResponse
import io.vucos.mobile.domain.repository.catalog.ICatalogRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogRepository @Inject constructor(private val api: CatalogApi, private val safeApiCall: SafeApiCall) :
    ICatalogRepository {
    override suspend fun GetCatalogHome(apiKey: String): Resource<CatalogHomeResponse> = safeApiCall.execute {
        api.getCatalogHome(apiKey);
    }

}

package io.vucos.mobile.domain.repository.catalog

import io.vucos.mobile.domain.model.catalog.CatalogHomeResponse
import io.vucos.mobile.util.Resource

interface ICatalogRepository {
    suspend fun GetCatalogHome(apiKey: String): Resource<CatalogHomeResponse>
}
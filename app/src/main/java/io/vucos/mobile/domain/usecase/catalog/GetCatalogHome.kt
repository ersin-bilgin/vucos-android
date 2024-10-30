package io.vucos.mobile.domain.usecase.catalog

import io.vucos.mobile.domain.model.catalog.CatalogHomeResponse
import io.vucos.mobile.domain.repository.catalog.ICatalogRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCatalogHome @Inject constructor(private val catalogRepository : ICatalogRepository)
{
    operator fun invoke(apiKey:String) : Flow<Resource<CatalogHomeResponse>> = flow {
        emit(
            catalogRepository.GetCatalogHome(apiKey)
        )
    }
}
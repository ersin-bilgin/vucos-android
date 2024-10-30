package io.vucos.mobile.data.repository.resources

import io.vucos.mobile.data.api.resources.ResourcesApi
import io.vucos.mobile.domain.model.resources.genres.GenresData
import io.vucos.mobile.domain.repository.resources.IResourcesRepository
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.SafeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesRepository @Inject constructor(private val api: ResourcesApi, private val safeApiCall: SafeApiCall) :
    IResourcesRepository {
    override suspend fun GetAllGenres(apiKey: String): Resource<GenresData> = safeApiCall.execute {
        api.getAllGenres(apiKey)
    }
}
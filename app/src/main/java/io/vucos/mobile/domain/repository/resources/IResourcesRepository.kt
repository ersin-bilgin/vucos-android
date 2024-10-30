package io.vucos.mobile.domain.repository.resources

import io.vucos.mobile.domain.model.resources.genres.GenresData
import io.vucos.mobile.util.Resource

interface IResourcesRepository {
    suspend fun GetAllGenres(apiKey: String): Resource<GenresData>
}
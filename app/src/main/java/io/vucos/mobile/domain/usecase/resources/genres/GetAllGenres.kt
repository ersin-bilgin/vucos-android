package io.vucos.mobile.domain.usecase.resources.genres

import io.vucos.mobile.domain.model.resources.genres.GenresData
import io.vucos.mobile.domain.repository.resources.IResourcesRepository
import io.vucos.mobile.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllGenres @Inject constructor(private val ResourcesRepository: IResourcesRepository)
{
    operator fun invoke(apiKey:String) : Flow<Resource<GenresData>> = flow {
        emit(
            ResourcesRepository.GetAllGenres(apiKey)
        )
    }
}
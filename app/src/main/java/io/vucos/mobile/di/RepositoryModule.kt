package io.vucos.mobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.vucos.mobile.data.repository.application.ApplicationRepository
import io.vucos.mobile.data.repository.authentication.AuthenticationRepository
import io.vucos.mobile.data.repository.catalog.CatalogRepository
import io.vucos.mobile.data.repository.profiles.ProfilesRepository
import io.vucos.mobile.data.repository.recommendation.RecommendationRepository
import io.vucos.mobile.data.repository.resources.ResourcesRepository
import io.vucos.mobile.data.repository.vod.VodRepository
import io.vucos.mobile.domain.repository.application.IApplicationRepository
import io.vucos.mobile.domain.repository.authentication.IAuthenticationRepository
import io.vucos.mobile.domain.repository.catalog.ICatalogRepository
import io.vucos.mobile.domain.repository.profiles.IProfilesRepository
import io.vucos.mobile.domain.repository.recommendation.IRecommendationRepository
import io.vucos.mobile.domain.repository.resources.IResourcesRepository
import io.vucos.mobile.domain.repository.vod.IVodRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthenticationRepository(repository: AuthenticationRepository): IAuthenticationRepository

    @Binds
    abstract fun bindProfilesRepository(repository: ProfilesRepository): IProfilesRepository

    @Binds
    abstract fun bindApplicationRepository(repository: ApplicationRepository): IApplicationRepository

    @Binds
    abstract fun bindCatalogRepository(repository: CatalogRepository): ICatalogRepository

    @Binds
    abstract fun bindVodRepository(repository: VodRepository): IVodRepository

    @Binds
    abstract fun bindRecommendationRepository(repository: RecommendationRepository): IRecommendationRepository

    @Binds
    abstract fun bindResourcesRepository(repository: ResourcesRepository): IResourcesRepository
}
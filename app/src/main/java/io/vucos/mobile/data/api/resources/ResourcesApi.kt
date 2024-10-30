package io.vucos.mobile.data.api.resources

import io.vucos.mobile.domain.model.resources.genres.GenresData
import retrofit2.http.GET
import retrofit2.http.Header

interface ResourcesApi {
    @GET("api/resources/all-genres")
    suspend fun getAllGenres(@Header("Authorization") apiKey: String): GenresData
}
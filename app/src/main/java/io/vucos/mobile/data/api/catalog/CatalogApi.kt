package io.vucos.mobile.data.api.catalog

import io.vucos.mobile.domain.model.catalog.CatalogHomeResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface CatalogApi {
    @GET("api/catalog/home")
    suspend fun getCatalogHome(@Header("Authorization") apiKey: String): CatalogHomeResponse
}
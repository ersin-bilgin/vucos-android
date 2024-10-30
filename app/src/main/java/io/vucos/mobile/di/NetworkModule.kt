package io.vucos.mobile.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.vucos.mobile.data.api.application.ApplicationApi
import io.vucos.mobile.data.api.authentication.AuthenticationApi
import io.vucos.mobile.data.api.catalog.CatalogApi
import io.vucos.mobile.data.api.profiles.ProfilesApi
import io.vucos.mobile.data.api.recommendation.RecommendationApi
import io.vucos.mobile.data.api.resources.ResourcesApi
import io.vucos.mobile.data.api.vod.VodApi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "http://10.100.255.183:8080/"
    private const val QUERY_LANGUAGE = "en"
    private const val IMAGE_LANGUAGE = "en,null"

    private const val CACHE_SIZE = 1024 * 1024 * 10L // 10 MB
    private const val CACHE_MAX_AGE = 60 * 60  // 1 hour
    private const val CACHE_MAX_STALE = 60 * 60 * 24 * 7 // 7 days

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

            with(networkCapabilities) {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                    else -> false
                }
            }
        } else {
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
    }


    @Singleton
    @Provides
    fun provideInterceptor(@ApplicationContext context: Context): Interceptor =
        Interceptor { chain ->
            val url = chain.request()
                .url()
                .newBuilder()
                .build()

            val headerName = "Cache-Control"
            val headerValue =
                if (isNetworkAvailable(context)) "public, max-age=$CACHE_MAX_AGE"
                else "public, only-if-cached, max-stale=$CACHE_MAX_STALE"

            val request = chain.request()
                .newBuilder()
                .url(url)
                .header(headerName, headerValue)
                .build()

            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context, interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, CACHE_SIZE))
            .addInterceptor(interceptor)
            .build()

    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .create()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideAuthenticationApi(retrofit: Retrofit): AuthenticationApi =
        retrofit.create(AuthenticationApi::class.java)

    @Singleton
    @Provides
    fun provideProfilesApi(retrofit: Retrofit): ProfilesApi =
        retrofit.create(ProfilesApi::class.java)

    @Singleton
    @Provides
    fun provideApplicationApi(retrofit: Retrofit): ApplicationApi =
        retrofit.create(ApplicationApi::class.java)

    @Singleton
    @Provides
    fun provideVodApi(retrofit: Retrofit): VodApi =
        retrofit.create(VodApi::class.java)

    @Singleton
    @Provides
    fun provideCatalogApi(retrofit: Retrofit): CatalogApi =
        retrofit.create(CatalogApi::class.java)

    @Singleton
    @Provides
    fun provideResourcesApi(retrofit: Retrofit): ResourcesApi =
        retrofit.create(ResourcesApi::class.java)

    @Singleton
    @Provides
    fun provideRecommendationApi(retrofit: Retrofit): RecommendationApi =
        retrofit.create(RecommendationApi::class.java)

}
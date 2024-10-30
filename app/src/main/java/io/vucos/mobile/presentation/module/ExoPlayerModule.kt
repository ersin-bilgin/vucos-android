package io.vucos.mobile.presentation.module

import android.content.Context
import androidx.media3.common.C
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExoPlayerModule {

    @Provides
    @Singleton
    fun provideExoPlayer(
        @ApplicationContext context: Context
    ): ExoPlayer = ExoPlayer.Builder(context)
        .setHandleAudioBecomingNoisy(true) // Kulaklık çıkarıldığında oynatmayı durdur
        .setWakeMode(C.WAKE_MODE_NETWORK) // Ağ erişimi için wake lock
        .build()
}
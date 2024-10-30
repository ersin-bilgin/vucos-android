package io.vucos.mobile.presentation.screens.play_movie

import android.content.ContentValues.TAG
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentMoviePlayerBinding
import io.vucos.mobile.presentation.base.BaseFragment
import javax.inject.Inject


@UnstableApi
@AndroidEntryPoint
class PlayMovieFragment : BaseFragment<FragmentMoviePlayerBinding>(R.layout.fragment_movie_player) {
    override val viewModel: PlayMovieViewModel by viewModels()
    private var isControllerVisible = true
    private val controllerTimeout = 3000L
    private var player: ExoPlayer? = null
    private lateinit var insetsController: WindowInsetsControllerCompat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFullscreen()
        setLandscapeOrientation()
        initializePlayer()
        setupControllerVisibility()
        observeViewModel()
    }
    private val controllerViews by lazy {
        listOf(
            binding.playerView.findViewById<View>(R.id.topBar),
            binding.playerView.findViewById<View>(R.id.channelInfo),
            binding.playerView.findViewById<View>(R.id.centerControls),
            binding.playerView.findViewById<View>(R.id.bottomControls)
        )
    }

    private fun setupControllerVisibility() {
        binding.playerView.apply {
            // ExoPlayer'ın kendi controller timeout'unu devre dışı bırak
            controllerShowTimeoutMs = 0
            controllerHideOnTouch = false

            // Touch listener ekle
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        toggleControllerVisibility()
                        true
                    }
                    else -> false
                }
            }
        }
    }
    private fun toggleControllerVisibility() {
        if (!isControllerVisible) {
            showControllers()
        }
        // Her dokunuşta timer'ı resetle
        controllerHandler.removeCallbacks(hideControllersRunnable)
        controllerHandler.postDelayed(hideControllersRunnable, controllerTimeout)
    }

    private val controllerHandler = Handler(Looper.getMainLooper())
    private val hideControllersRunnable = Runnable {
        hideControllers()
    }

    private fun showControllers() {
        isControllerVisible = true
        controllerViews.forEach { view ->
            view?.let {
                // Blur animasyonu
                it.alpha = 0f
                it.visibility = View.VISIBLE
                it.animate()
                    .alpha(1f)
                    .setDuration(300)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withStartAction {
                        // Blur effect başlangıcı
                        val blurParams = it.background?.mutate() as? RippleDrawable
                        blurParams?.alpha = 0
                    }
                    .withEndAction {
                        // Blur effect sonu
                        val blurParams = it.background?.mutate() as? RippleDrawable
                        blurParams?.alpha = 255
                    }
                    .start()
            }
        }
    }

    private fun hideControllers() {
        if (!isControllerVisible) return

        isControllerVisible = false
        controllerViews.forEach { view ->
            view?.let {
                it.animate()
                    .alpha(0f)
                    .setDuration(300)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .withStartAction {
                        // Blur effect başlangıcı
                        val blurParams = it.background?.mutate() as? RippleDrawable
                        blurParams?.alpha = 255
                    }
                    .withEndAction {
                        it.visibility = View.GONE
                        // Blur effect sonu
                        val blurParams = it.background?.mutate() as? RippleDrawable
                        blurParams?.alpha = 0
                    }
                    .start()
            }
        }
    }

    @UnstableApi
    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .setSeekBackIncrementMs(10000) // 10 saniye geri
            .setSeekForwardIncrementMs(10000) // 10 saniye ileri
            .build()
            .also { exoPlayer ->
                binding.playerView.player = exoPlayer
                setupCustomControls() // Kontrolleri ayarla

                val videoUrl = "https://demo.unified-streaming.com/k8s/features/stable/video/tears-of-steel/tears-of-steel.mp4/.m3u8"

                try {
                    val mediaItem = MediaItem.Builder()
                        .setUri(videoUrl)
                        .setMimeType(MimeTypes.APPLICATION_M3U8)
                        .build()

                    exoPlayer.apply {
                        setMediaItem(mediaItem)
                        addListener(object : Player.Listener {
                            override fun onPlaybackStateChanged(playbackState: Int) {
                                when (playbackState) {
                                    Player.STATE_READY -> {
                                        hideLoading()
                                        viewModel.setPlayingState(true)
                                    }
                                    Player.STATE_BUFFERING -> {
                                        showLoading()
                                    }
                                    Player.STATE_ENDED -> {
                                        hideLoading()
                                        viewModel.setPlayingState(false)
                                    }
                                    Player.STATE_IDLE -> {
                                        hideLoading()
                                        viewModel.setPlayingState(false)
                                    }
                                }
                            }

                            override fun onIsPlayingChanged(isPlaying: Boolean) {
                                viewModel.setPlayingState(isPlaying)
                            }

                            override fun onPlayerError(error: PlaybackException) {
                                error.printStackTrace()
                                hideLoading()
                                Toast.makeText(
                                    requireContext(),
                                    "Video yüklenemedi: ${error.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })

                        prepare()
                        playWhenReady = true
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        requireContext(),
                        "Video yüklenemedi: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun observeViewModel() {
        viewModel.isPlaying.observe(viewLifecycleOwner) { isPlaying ->
            // İsterseniz play/pause durumuna göre UI güncellemesi yapabilirsiniz
        }

        viewModel.currentPosition.observe(viewLifecycleOwner) { position ->
            // İsterseniz current position değişimini takip edebilirsiniz
        }

        viewModel.duration.observe(viewLifecycleOwner) { duration ->
            // İsterseniz video süresini takip edebilirsiniz
        }
    }
    private fun createBlurBackground(): Drawable {
        return RippleDrawable(
            ColorStateList.valueOf(Color.TRANSPARENT),
            null,
            ColorDrawable(Color.parseColor("#80000000"))
        ).apply {
            alpha = 0
        }
    }
    private fun setupCustomControls() {
        binding.playerView.apply {
            useController = true
            controllerShowTimeoutMs = 3000
            controllerHideOnTouch = true

            findViewById<ImageView>(R.id.channelLogo)?.let { logoView ->
                Glide.with(this)
                    .load("https://brandslogos.com/wp-content/uploads/images/large/tlc-logo.png")
                    .placeholder(R.drawable.test_channel_logo) // Yüklenene kadar gösterilecek placeholder
                    .error(R.drawable.test_channel_logo) // Hata durumunda gösterilecek resim
                    .into(logoView)
            }

            // Geri butonu
            findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener {
                findNavController().popBackStack()
            }

            // 30sn geri/ileri sarma butonları
            findViewById<ImageButton>(R.id.rewind10)?.setOnClickListener {
                player?.seekBack() // 30 saniye geri
            }

            findViewById<ImageButton>(R.id.forward10)?.setOnClickListener {
                player?.seekForward() // 30 saniye ileri
            }

            // 10sn geri/ileri sarma butonları
            findViewById<ImageButton>(R.id.rewind10)?.setOnClickListener {
                player?.seekTo(maxOf(0, player?.currentPosition?.minus(10000) ?: 0))
            }
            controllerViews.forEach { view ->
                view?.background = createBlurBackground()
            }
            findViewById<ImageButton>(R.id.forward10)?.setOnClickListener {
                player?.currentPosition?.let { currentPosition ->
                    player?.duration?.let { duration ->
                        player?.seekTo(minOf(duration, currentPosition + 10000))
                    }
                }
            }

            // Üst kısımdaki action butonlar
            findViewById<ImageButton>(R.id.btnAction1)?.setOnClickListener {
                // Action 1 işlemi
            }

            findViewById<ImageButton>(R.id.btnAction2)?.setOnClickListener {
                // Action 2 işlemi
            }

            findViewById<ImageButton>(R.id.btnAction3)?.setOnClickListener {
                // Action 3 işlemi
            }

            // Kanal bilgilerini set et
            findViewById<ImageView>(R.id.channelLogo)?.let { logo ->
                // Logo set etme işlemi
                // Glide.with(this).load(logoUrl).into(logo)
            }

            findViewById<TextView>(R.id.channelName)?.text = "Kanal Adı"
        }
    }

    private fun setupFullscreen() {
        requireActivity().window.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            insetsController = WindowInsetsControllerCompat(window, binding.root)

            insetsController.apply {
                hide(WindowInsetsCompat.Type.systemBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }
    }

    private fun setLandscapeOrientation() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        controllerHandler.removeCallbacks(hideControllersRunnable)
        player?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        controllerHandler.removeCallbacks(hideControllersRunnable)
        releasePlayer()
        resetScreenSettings()
    }

    private fun releasePlayer() {
        try {
            player?.let { exoPlayer ->
                exoPlayer.stop()
                exoPlayer.clearMediaItems()
                exoPlayer.release()
            }
            player = null
            binding.playerView.player = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun resetScreenSettings() {
        if (::insetsController.isInitialized) {
            requireActivity().window.let { window ->
                WindowCompat.setDecorFitsSystemWindows(window, true)
                insetsController.show(WindowInsetsCompat.Type.systemBars())
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    private fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loading.visibility = View.GONE
    }
}

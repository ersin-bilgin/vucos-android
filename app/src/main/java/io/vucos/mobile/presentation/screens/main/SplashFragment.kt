package io.vucos.mobile.presentation.screens.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import kotlin.random.Random


@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val splashImages = listOf(
        R.drawable.splash1,
        R.drawable.splash2,
        R.drawable.splash3,
        R.drawable.splash4
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Rastgele bir splash resmi seç
        val randomSplashImage = splashImages[Random.nextInt(splashImages.size)]
        view.findViewById<ImageView>(R.id.activity_splash_image_view).setImageResource(randomSplashImage)

        // 4 saniye sonra WelcomeFragment'a yönlendir
        Handler(Looper.getMainLooper()).postDelayed({
            view.findViewById<FrameLayout>(R.id.splash_layout).visibility = View.GONE
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
        }, 4000)
    }
}
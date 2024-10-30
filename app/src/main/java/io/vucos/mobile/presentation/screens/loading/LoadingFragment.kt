package io.vucos.mobile.presentation.screens.loading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentLoadingBinding
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.screens.signin.EmailSigninViewModel
import io.vucos.mobile.presentation.screens.who_will_watch.WhoWillWatchViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoadingFragment : BaseFragment<FragmentLoadingBinding>(R.layout.fragment_loading) {
    override val viewModel: LoadingViewModel by viewModels()
    private val destinationId: Int by lazy { arguments?.getInt("destinationId") ?: -1 }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("LoadingFragment", "onViewCreated called with destinationId: $destinationId")

        // 4 saniye sonra hedef fragmenta geçiş yap
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToDestination()
        }, 4000)
    }

    private fun navigateToDestination() {
        if (destinationId == -1) {
            Log.e("LoadingFragment", "Invalid destination ID")
            findNavController().popBackStack()
            return
        }

        Log.d("LoadingFragment", "Attempting to navigate to destination: $destinationId")
        try {
            findNavController().navigate(destinationId)
        } catch (e: Exception) {
            Log.e("LoadingFragment", "Failed to navigate to destination: $destinationId", e)
            findNavController().popBackStack()
        }
    }
}
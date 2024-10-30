package io.vucos.mobile.presentation.screens.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSigninBinding
import io.vucos.mobile.presentation.adapters.SinginLoginPagerAdapter
import io.vucos.mobile.presentation.base.BaseFragment


@AndroidEntryPoint
class SigninFragment : BaseFragment<FragmentSigninBinding>(R.layout.fragment_signin) {
    override val viewModel: SigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        setupBackButton()
        observeChildFragmentNavigation()
    }

    private fun setupViewPager() {
        val adapter = SinginLoginPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.signInTabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Email"
                1 -> "Phone Number"
                else -> null
            }
        }.attach()
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack(R.id.welcomeFragment, false)
        }
    }
    private fun navigateToDestination(destinationId: Int) {
        Log.d("SigninFragment", "Attempting to navigate to destination: $destinationId")
        try {
            findNavController().navigate(destinationId)
        } catch (e: Exception) {
            Log.e("SigninFragment", "Navigation failed", e)
        }
    }
    private fun observeChildFragmentNavigation() {
        childFragmentManager.setFragmentResultListener("navigation", viewLifecycleOwner) { _, bundle ->
            val navigationBundle = bundle.getBundle("navigationBundle")
            navigationBundle?.let { navBundle ->
                val destinationId = navBundle.getInt("destinationId")
                Log.d("SigninFragment", "Received navigation request to destination: $destinationId")
                navigateToLoadingFragment(destinationId)
            }
        }
    }

    private fun navigateToLoadingFragment(finalDestinationId: Int) {
        Log.d("SigninFragment", "Attempting to navigate to LoadingFragment")
        try {
            val bundle = Bundle().apply {
                putInt("destinationId", finalDestinationId)
            }
            findNavController().navigate(R.id.action_signinFragment_to_loadingFragment, bundle)
        } catch (e: Exception) {
            Log.e("SigninFragment", "Navigation failed", e)
            // Hata durumunda kullanıcıya bir şeyler göstermek isterseniz burada bir işlem yapabilirsiniz
        }
    }
}
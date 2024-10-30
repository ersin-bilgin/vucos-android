package io.vucos.mobile.presentation.screens.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSigninEmailBinding
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.base.UiState
import io.vucos.mobile.util.navigateWithLoading
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EmailSigninFragment : BaseFragment<FragmentSigninEmailBinding>(R.layout.fragment_signin_email) {
    override val viewModel: EmailSigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("EmailSigninFragment", "onViewCreated called")

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupSignInButton()
        observeUiState()
        observeErrorEvent()
        observeLoginSuccess()
    }

    private fun setupSignInButton() {
        Log.d("EmailSigninFragment", "setupSignInButton called")
        binding.signInButtonEmail?.setOnClickListener {
            Log.d("EmailSigninFragment", "Sign in button clicked")
            viewModel.login()
        }
    }

    private fun observeLoginSuccess() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginSuccess.collect { success ->
                if (success) {
                    Log.d("EmailSigninFragment", "Login successful, requesting navigation")
                    requestNavigationToLoadingFragment()
                } else {
                    Log.d("EmailSigninFragment", "Login failed")
                    showSnackbar("Login failed. Please try again.", indefinite = true)
                }
            }
        }
    }

    private fun requestNavigationToLoadingFragment() {
        Log.d("EmailSigninFragment", "Requesting navigation to LoadingFragment")
        val bundle = bundleOf("destinationId" to R.id.whoWillWatchFragment)
        parentFragmentManager.setFragmentResult("navigation", bundleOf("navigationBundle" to bundle))
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                Log.d("EmailSigninFragment", "UI State updated: $state")
                updateUi(state)
            }
        }
    }

    private fun updateUi(state: UiState) {
        binding.signInButtonEmail?.isEnabled = !state.isLoading
        if (state.isError) {
            showSnackbar(state.errorText ?: "An error occurred", indefinite = false)
        }
    }





    private fun observeErrorEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorEvent.collectLatest { errorMessage ->
                showSnackbar(errorMessage, indefinite = false)
            }
        }
    }
}
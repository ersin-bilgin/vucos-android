package io.vucos.mobile.presentation.screens.signup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSigninEmailBinding
import io.vucos.mobile.databinding.FragmentTermsAndPrivacyPolicyBinding
import io.vucos.mobile.presentation.base.BaseFragment


@AndroidEntryPoint
class TermsOfServiceAndPrivacyPolicyFragment :
    BaseFragment<FragmentTermsAndPrivacyPolicyBinding>(R.layout.fragment_terms_and_privacy_policy) {
    override val viewModel: TermsOfServiceAndPrivacyPolicyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBackButton()
    }
    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack(R.id.signupFragment, false)
        }
    }
}
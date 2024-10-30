package io.vucos.mobile.presentation.screens.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSignupPhoneBinding
import io.vucos.mobile.presentation.base.BaseFragment

@AndroidEntryPoint
class PhoneSignupFragment : BaseFragment<FragmentSignupPhoneBinding>(R.layout.fragment_signup_phone) {
    override val viewModel: PhoneSignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.phoneContinueButton.setOnClickListener {
            viewModel.login()
        }
    }
}
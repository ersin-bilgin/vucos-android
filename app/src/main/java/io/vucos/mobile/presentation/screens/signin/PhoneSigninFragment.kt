package io.vucos.mobile.presentation.screens.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSigninPhoneBinding
import io.vucos.mobile.presentation.base.BaseFragment

@AndroidEntryPoint
class PhoneSigninFragment : BaseFragment<FragmentSigninPhoneBinding>(R.layout.fragment_signin_phone) {
    override val viewModel: PhoneSigninViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.signInButton.setOnClickListener {
            viewModel.login()
        }
    }
}
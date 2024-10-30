package io.vucos.mobile.presentation.screens.signup

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentSignupBinding
import io.vucos.mobile.presentation.adapters.SingupLoginPagerAdapter
import io.vucos.mobile.presentation.base.BaseFragment

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {
    override val viewModel: SignupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        setupBackButton()
        observeChildFragmentNavigation()
        setupTermsAndPrivacyText()
    }

    private fun setupViewPager() {
        val adapter = SingupLoginPagerAdapter(this)
        binding.signupViewPager.adapter = adapter

        TabLayoutMediator(binding.signUpTabLayout, binding.signupViewPager) { tab, position ->
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
        Log.d("SignupFragment", "Attempting to navigate to destination: $destinationId")
        try {
            findNavController().navigate(destinationId)
        } catch (e: Exception) {
            Log.e("SigninFragment", "Navigation failed", e)
        }
    }
    private fun setupTermsAndPrivacyText() {
        val fullText = "By tapping Continue button I agree to the Terms of Service and Privacy Policy."
        val spannableString = SpannableString(fullText)

        val termsOfServiceClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_signupFragment_to_termsOfServiceAndPrivacyPolicyFragment)

                // Terms of Service'e tıklandığında yapılacak işlem
                Log.d("SignupFragment", "Terms of Service clicked")
                // Örneğin: navigateToTermsOfService()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.flat_yellow_1)
                ds.isFakeBoldText = true
                ds.isUnderlineText = false
            }
        }

        val privacyPolicyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                findNavController().navigate(R.id.action_signupFragment_to_termsOfServiceAndPrivacyPolicyFragment)

                // Privacy Policy'ye tıklandığında yapılacak işlem
                Log.d("SignupFragment", "Privacy Policy clicked")
                // Örneğin: navigateToPrivacyPolicy()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = ContextCompat.getColor(requireContext(), R.color.flat_yellow_1)
                ds.isFakeBoldText = true
                ds.isUnderlineText = false
            }
        }

        val termsStart = fullText.indexOf("Terms of Service")
        val termsEnd = termsStart + "Terms of Service".length
        val privacyStart = fullText.indexOf("Privacy Policy")
        val privacyEnd = privacyStart + "Privacy Policy".length

        spannableString.setSpan(termsOfServiceClickableSpan, termsStart, termsEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(privacyPolicyClickableSpan, privacyStart, privacyEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.termsTextView.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
            textSize = 12f
            setTextColor(ContextCompat.getColor(requireContext(), R.color.switch_thumb_disabled_material_dark))
            typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_semibold)
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
            findNavController().navigate(R.id.action_signupFragment_to_loadingFragment, bundle)
        } catch (e: Exception) {
            Log.e("SigninFragment", "Navigation failed", e)
            // Hata durumunda kullanıcıya bir şeyler göstermek isterseniz burada bir işlem yapabilirsiniz
        }
    }
}
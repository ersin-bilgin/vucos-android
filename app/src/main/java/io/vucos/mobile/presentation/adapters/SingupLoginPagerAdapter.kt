package io.vucos.mobile.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.vucos.mobile.presentation.screens.signup.EmailSignupFragment
import io.vucos.mobile.presentation.screens.signup.PhoneSignupFragment

class SingupLoginPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EmailSignupFragment()
            1 -> PhoneSignupFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
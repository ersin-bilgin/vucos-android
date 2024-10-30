package io.vucos.mobile.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.vucos.mobile.presentation.screens.signin.EmailSigninFragment
import io.vucos.mobile.presentation.screens.signin.PhoneSigninFragment

class SinginLoginPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EmailSigninFragment()
            1 -> PhoneSigninFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
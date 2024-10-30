package io.vucos.mobile.presentation.screens.for_you

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentForYouBinding
import io.vucos.mobile.databinding.FragmentHomeBinding
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.screens.home.HomeViewModel

@AndroidEntryPoint
class ForYouFragment : BaseFragment<FragmentForYouBinding>(R.layout.fragment_for_you) {
    override val viewModel: ForYouViewModel by viewModels()

}
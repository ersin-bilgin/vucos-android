package io.vucos.mobile.presentation.screens.live_tv

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentLiveTvBinding
import io.vucos.mobile.presentation.base.BaseFragment


@AndroidEntryPoint
class LiveTvFragment : BaseFragment<FragmentLiveTvBinding>(R.layout.fragment_live_tv) {
    override val viewModel: LiveTvViewModel by viewModels()
}
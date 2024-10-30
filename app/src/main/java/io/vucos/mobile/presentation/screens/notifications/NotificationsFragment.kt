package io.vucos.mobile.presentation.screens.notifications

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentMyListBinding
import io.vucos.mobile.databinding.FragmentNotificationsBinding
import io.vucos.mobile.presentation.base.BaseFragment

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>(R.layout.fragment_notifications) {
    override val viewModel: NotificationsViewModel by viewModels()
}
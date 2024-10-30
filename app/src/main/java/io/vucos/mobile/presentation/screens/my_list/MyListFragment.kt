package io.vucos.mobile.presentation.screens.my_list

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentMyListBinding
import io.vucos.mobile.presentation.base.BaseFragment


@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>(R.layout.fragment_my_list) {
    override val viewModel: MyListViewModel by viewModels()
}
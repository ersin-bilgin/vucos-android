package io.vucos.mobile.presentation.screens.movie_details

import io.vucos.mobile.presentation.adapters.home.BaseContentFragment

class ForYouContentFragment : BaseContentFragment() {
    override fun observeContent() {
        viewModel.forYouContent.observe(viewLifecycleOwner) { content ->
            adapter.submitList(content)
        }
    }

    override fun loadContent() {
        viewModel.getTabContent(1) // 1 index'i size özel içerik için
    }
}
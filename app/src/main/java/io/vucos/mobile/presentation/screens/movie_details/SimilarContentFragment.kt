package io.vucos.mobile.presentation.screens.movie_details

import io.vucos.mobile.presentation.adapters.home.BaseContentFragment

class SimilarContentFragment : BaseContentFragment() {
    override fun observeContent() {
        viewModel.similarContent.observe(viewLifecycleOwner) { content ->
            adapter.submitList(content)
        }
    }

    override fun loadContent() {
        viewModel.getTabContent(0) // 0 index'i benzer içerik için
    }
}

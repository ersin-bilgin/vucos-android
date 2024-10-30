package io.vucos.mobile.presentation.screens.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import io.vucos.mobile.presentation.adapters.home.BaseContentFragment
import io.vucos.mobile.presentation.adapters.movie_details.EpisodesAdapter
import kotlinx.coroutines.launch

class EpisodesFragment : BaseContentFragment() {
    private lateinit var episodesAdapter: EpisodesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("EpisodesFragment", "onViewCreated called")
        setupEpisodesRecyclerView()
        observeContent()
        loadContent()
    }

    private fun setupEpisodesRecyclerView() {
        episodesAdapter = EpisodesAdapter()
        binding.contentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = episodesAdapter
        }
        Log.d("EpisodesFragment", "RecyclerView setup completed")
    }

    override fun observeContent() {
        Log.d("EpisodesFragment", "observeContent called")
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                (viewModel as? MovieDetailsViewModel)?.episodesList?.observe(viewLifecycleOwner) { episodes ->
                    Log.d("EpisodesFragment", "Episodes received: ${episodes?.size}")
                    episodesAdapter.submitList(episodes)
                }
            }
        }
    }

    override fun loadContent() {
        Log.d("EpisodesFragment", "loadContent called")
        (viewModel as? MovieDetailsViewModel)?.let { viewModel ->
            val movieId = arguments?.getString("movieId")
            viewModel.getVodDetails(movieId)
        }
    }
}
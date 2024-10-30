package io.vucos.mobile.presentation.adapters.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.vucos.mobile.databinding.FragmentContentGridBinding
import io.vucos.mobile.presentation.adapters.movie_details.ContentAdapter
import io.vucos.mobile.presentation.screens.movie_details.MovieDetailsViewModel

abstract class BaseContentFragment : Fragment() {
    protected lateinit var viewModel: MovieDetailsViewModel
    protected lateinit var binding: FragmentContentGridBinding
    protected lateinit var adapter: ContentAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentContentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MovieDetailsViewModel::class.java)
        setupRecyclerView()
        observeContent()
        loadContent()
    }

    private fun setupRecyclerView() {
        adapter = ContentAdapter {}
        binding.contentRecyclerView.apply {
            layoutManager = createLayoutManager()
            adapter = this@BaseContentFragment.adapter
        }
    }

    protected open fun createLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 3)
    }

    abstract fun observeContent()
    abstract fun loadContent()
}

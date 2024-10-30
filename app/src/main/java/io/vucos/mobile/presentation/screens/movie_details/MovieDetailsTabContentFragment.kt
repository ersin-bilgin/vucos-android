package io.vucos.mobile.presentation.screens.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import io.vucos.mobile.databinding.FragmentMovieDetailsTabContentBinding
import io.vucos.mobile.presentation.adapters.movie_details.ContentAdapter

class MovieDetailsTabContentFragment : Fragment() {
    private val viewModel: MovieDetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentMovieDetailsTabContentBinding
    private lateinit var contentAdapter: ContentAdapter
    private var tabPosition: Int = 0

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): MovieDetailsTabContentFragment {
            return MovieDetailsTabContentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POSITION, position)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMovieDetailsTabContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabPosition = arguments?.getInt(ARG_POSITION) ?: 0
        setupRecyclerView()
        observeContent()
    }

    private fun setupRecyclerView() {
        contentAdapter = ContentAdapter {
        }
        binding.contentRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = contentAdapter
            // ItemDecoration ekleme kodu buraya gelecek
        }
    }

    private fun observeContent() {
        when (tabPosition) {
            0 -> viewModel.similarContent.observe(viewLifecycleOwner) { content ->
                contentAdapter.submitList(content)
            }
            1 -> viewModel.forYouContent.observe(viewLifecycleOwner) { content ->
                contentAdapter.submitList(content)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTabContent(tabPosition)
    }
}
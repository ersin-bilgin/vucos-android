package io.vucos.mobile.presentation.screens.who_will_watch

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentWhoWillWatchBinding
import io.vucos.mobile.presentation.adapters.who_will_watch.WhoWillWatchAdapter
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.navigation_events.HomeMainNavigationEvent


@AndroidEntryPoint
class WhoWillWatchFragment : BaseFragment<FragmentWhoWillWatchBinding>(R.layout.fragment_who_will_watch) {
    override val viewModel: WhoWillWatchViewModel by viewModels()
    private lateinit var whoWillWatchAdapter: WhoWillWatchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        viewModel.loadData()
    }

    private fun setupRecyclerView() {
        whoWillWatchAdapter = WhoWillWatchAdapter(
            onProfileClicked = { profile ->
                viewModel.onProfileSelected(profile)
                //Toast.makeText(context, "Profile ${profile.name} selected", Toast.LENGTH_SHORT).show()

                // Handle profile selection
                // For example: viewModel.onProfileSelected(profile)
            },
            onAddProfileClick = {
                // Handle add profile action
                Toast.makeText(context, "Add Profile clicked", Toast.LENGTH_SHORT).show()
                // You might want to navigate to a new screen or show a dialog here
                // For example: navigateToAddProfileScreen()
            }
        )
        binding.profileRecyclerView.adapter = whoWillWatchAdapter
    }

    private fun observeViewModel() {
        viewModel.profiles.observe(viewLifecycleOwner) { profiles ->
            whoWillWatchAdapter.submitList(profiles)
        }

        viewModel.isDataReady.observe(viewLifecycleOwner) { isReady ->
            binding.profileRecyclerView.visibility = if (isReady) View.VISIBLE else View.GONE
        }


        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is HomeMainNavigationEvent.NavigateToMainContent -> {
                    findNavController().navigate(R.id.action_fragmentWhoWillWatch_to_mainContentFragment)
                }
                // Diğer navigation eventleri buraya eklenebilir
                else -> {}
            }
        }

    }
}
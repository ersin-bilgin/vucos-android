package io.vucos.mobile.presentation.screens.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentMovieDetailsBinding
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.navigation_events.DetailsNavigationEvent

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>(R.layout.fragment_movie_details) {
    override val viewModel: MovieDetailsViewModel by viewModels()
    private var movieId: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieId = arguments?.getString("movieId")
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.setVodUId(movieId)
        viewModel.getVodDetails(movieId)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d("TopRatedDetailsFragment", "Geri tuşuna basıldı")
            viewModel.navigateToHome()
        }
        setupViewPager()
        observeNavigationEvents()
        observeMovieDetails()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Örnek: Like butonu için click listener
        binding.likeToggle.setOnClickListener {
            viewModel.onLikeClicked()
        }
        /*

        // Örnek: Play butonu için click listener
        binding.playButton.setOnClickListener {
            viewModel.onPlayClicked(movieId)
        }

        // Örnek: Poster image için click listener
        binding.moviePosterImageView.setOnClickListener {
            viewModel.onPosterClicked(movieId)
        }

        // Örnek: Toolbar'daki geri butonu için click listener
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        */
    }


    private fun observeNavigationEvents() {
        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is DetailsNavigationEvent.NavigateToHome -> navigateToHome()
                // Diğer navigasyon eventleri buraya eklenebilir
            }
        }
    }

    private fun observeMovieDetails() {

        viewModel.title.observe(viewLifecycleOwner) { title ->
            Log.d("MovieDetailsFragment", "Movie title: $title")
            // Gerekirse ek işlemler yapabilirsiniz
        }

        viewModel.platformRemoveDate.observe(viewLifecycleOwner) { title ->
            Log.d("MovieDetailsFragment", "Movie title: $title")
            // Gerekirse ek işlemler yapabilirsiniz
        }

        viewModel.platformReleaseYear.observe(viewLifecycleOwner) { title ->
            Log.d("MovieDetailsFragment", "Movie title: $title")
            // Gerekirse ek işlemler yapabilirsiniz
        }

        viewModel.duration.observe(viewLifecycleOwner) { title ->
            Log.d("MovieDetailsFragment", "Movie title: $title")
            // Gerekirse ek işlemler yapabilirsiniz
        }


        viewModel.originalTitle.observe(viewLifecycleOwner) { originalTitle ->
            Log.d("MovieDetailsFragment", "Original title: $originalTitle")
            // Gerekirse ek işlemler yapabilirsiniz
        }

        viewModel.posterUrl.observe(viewLifecycleOwner) { posterUrl ->
            Log.d("MovieDetailsFragment", "Poster URL: $posterUrl")
            // Burada poster resmini yüklemek için bir işlem yapabilirsiniz
            // Örneğin: Glide veya Picasso kullanarak
        }
    }

    private fun navigateToHome() {
        Log.d("TopRatedDetailsFragment", "navigateToHome() çağrıldı")
        try {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_homeFragment)
        } catch (e: Exception) {
            Log.e("TopRatedDetailsFragment", "Home'a navigasyon hatası", e)
        }
    }

    private fun setupViewPager() {
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout1

        viewModel.seasonNumber.observe(viewLifecycleOwner) { seasonNumber ->
            Log.d("MovieDetailsFragment", "Season number changed: $seasonNumber")
            val fragmentList = mutableListOf<Fragment>()

            if (seasonNumber > 0) {
                val episodesFragment = EpisodesFragment().apply {
                    arguments = Bundle().apply {
                        putString("movieId", movieId)
                    }
                }
                fragmentList.add(episodesFragment)
                Log.d("MovieDetailsFragment", "EpisodesFragment added")
            }

            fragmentList.add(SimilarContentFragment())
            fragmentList.add(ForYouContentFragment())

            val adapter = object : FragmentStateAdapter(this) {
                override fun getItemCount(): Int = fragmentList.size
                override fun createFragment(position: Int): Fragment = fragmentList[position]
            }
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = fragmentList.size - 1

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when {
                    seasonNumber > 0 && position == 0 -> "Bölümler"
                    (seasonNumber > 0 && position == 1) || (seasonNumber == 0 && position == 0) -> "Benzerler"
                    (seasonNumber > 0 && position == 2) || (seasonNumber == 0 && position == 1) -> "Sana Özel"
                    else -> null
                }
            }.attach()

            // Eğer bölümler varsa, ilk olarak bölümler tabını seç
            if (seasonNumber > 0) {
                viewPager.setCurrentItem(0, false)
            }

            Log.d("MovieDetailsFragment", "ViewPager setup completed. Tabs: ${tabLayout.tabCount}")
        }
    }

}
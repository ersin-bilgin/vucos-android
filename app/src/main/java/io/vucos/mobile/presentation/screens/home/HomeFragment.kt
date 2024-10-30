package io.vucos.mobile.presentation.screens.home



import android.animation.ValueAnimator
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.vucos.mobile.R
import io.vucos.mobile.databinding.FragmentHomeBinding
import io.vucos.mobile.databinding.FragmentNotificationsBinding
import io.vucos.mobile.presentation.adapters.home.HomeWidgetAdapter
import io.vucos.mobile.presentation.adapters.home.SlideAdapter
import io.vucos.mobile.presentation.base.BaseFragment
import io.vucos.mobile.presentation.navigation_events.HomeNavigationEvent
import kotlin.math.log


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModels()

    private lateinit var slideAdapter: SlideAdapter
    private lateinit var homeWidgetAdapter: HomeWidgetAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated çağrıldı")
        setupViewPager()
        setupWidgetRecyclerView()
        setupShimmerLayout()
        setupTabs()
        observeViewModel()
        viewModel.fetchSlideItems()
    }

    private fun setupShimmerLayout() {
        // Shimmer'ı başlangıçta başlat
        binding.shimmerLayout.startShimmer()
    }

    private fun setupViewPager() {
        slideAdapter = SlideAdapter(
            onButtonClick = { buttonIndex ->
                when (buttonIndex) {
                    0 -> { navigateMoviePlayerFragment() }
                    1 -> { Log.d("HomeFRAGMENT","Add My List Button Clicked") }
                }
            },
            onImageLoaded = { bitmap ->
                updateGradientBackground(bitmap)
            }
        )

        binding.viewPager.adapter = slideAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.onSlideChanged(position)
                slideAdapter.getCurrentBitmap(position)?.let { bitmap ->
                    updateGradientBackground(bitmap)
                }
            }
        })
    }

    private fun updateGradientBackground(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            palette?.let {
                val dominantColor = it.getDominantColor(Color.BLACK)
                val gradient = GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    intArrayOf(
                        ColorUtils.setAlphaComponent(dominantColor, 128), // 50% opacity
                        ColorUtils.setAlphaComponent(dominantColor, 64),  // 25% opacity
                        Color.TRANSPARENT
                    )
                )
                binding.gradientBackground.background = gradient
            }
        }
    }

    private fun setupWidgetRecyclerView() {
        homeWidgetAdapter = HomeWidgetAdapter()
        binding.widgetsRecyclerView.adapter = homeWidgetAdapter
        binding.widgetsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        Log.d("HomeFragment", "onResume çağrıldı")
        viewModel.fetchSlideItems()
    }

    private fun observeViewModel() {
        // Loading state observer
        viewModel.isShimmerLoading.observe(viewLifecycleOwner) { isShimmerLoading ->
            with(binding) {
                if (isShimmerLoading) {
                    contentLayout.visibility = View.GONE
                    shimmerLayout.visibility = View.VISIBLE
                    shimmerLayout.startShimmer()
                } else {
                    shimmerLayout.stopShimmer()
                    shimmerLayout.visibility = View.GONE
                    contentLayout.visibility = View.VISIBLE
                }
            }
        }

        viewModel.slideItems.observe(viewLifecycleOwner) { items ->
            Log.d("HomeFragment", "Slide items güncellendi: ${items.size} öğe")
            slideAdapter.submitList(items)
        }

        viewModel.widgets.observe(viewLifecycleOwner) { widgets ->
            Log.d("HomeFragment", "Widgets güncellendi: ${widgets.size} öğe")
            homeWidgetAdapter.submitList(widgets)
        }

        viewModel.currentSlideIndex.observe(viewLifecycleOwner) { index ->
            binding.viewPager.setCurrentItem(index, true)
        }

        // Error observer
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                com.google.android.material.snackbar.Snackbar.make(
                    binding.root,
                    it,
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                ).setAction("Retry") {
                    viewModel.retry()
                }.show()
            }
        }

        viewModel.navigationEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is HomeNavigationEvent.ToTopRatedDetail -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToTopRatedDetailsFragment(event.movieId)
                    findNavController().navigate(action)
                }
                is HomeNavigationEvent.ToMovieDetail -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(event.movieId)
                    findNavController().navigate(action)
                }
            }
        }
    }
    private fun setupTabs() {
        var selectedTab: TextView? = null
        val indicatorColor = ContextCompat.getColor(requireContext(), R.color.flat_yellow_1)
        val defaultTextColor = ContextCompat.getColor(requireContext(), android.R.color.white)
        val selectedTextColor = ContextCompat.getColor(requireContext(), android.R.color.white)

        // Normal ve Bold fontları tanımla
        val normalTypeface = Typeface.create("roboto", Typeface.NORMAL)
        val boldTypeface = Typeface.create("roboto", Typeface.BOLD)

        val tabs = listOf(
            binding.tabAll,
            binding.tabSeries,
            binding.tabMovies,
            binding.tabLiveTV
        )

        fun updateIndicator(newTab: TextView) {
            // Eski seçili tab'ı normal hale getir
            selectedTab?.apply {
                setTextColor(defaultTextColor)
                typeface = normalTypeface
            }

            // Yeni tab'ı seçili hale getir
            newTab.apply {
                setTextColor(selectedTextColor)
                typeface = boldTypeface
            }

            // Indicator animasyonu
            val indicator = binding.tabIndicator
            indicator.post {
                val params = indicator.layoutParams as ViewGroup.MarginLayoutParams
                val animator = ValueAnimator.ofInt(
                    (selectedTab?.left ?: newTab.left),
                    newTab.left
                )

                animator.addUpdateListener { animation ->
                    params.marginStart = animation.animatedValue as Int
                    indicator.layoutParams = params
                }

                // Genişlik animasyonu
                val widthAnimator = ValueAnimator.ofInt(
                    indicator.width,
                    newTab.width
                )

                widthAnimator.addUpdateListener { animation ->
                    params.width = animation.animatedValue as Int
                    indicator.layoutParams = params
                }

                // Animasyon ayarları
                animator.duration = 400
                widthAnimator.duration = 400
                animator.interpolator = FastOutSlowInInterpolator()
                widthAnimator.interpolator = FastOutSlowInInterpolator()

                // Animasyonları başlat
                animator.start()
                widthAnimator.start()
            }

            selectedTab = newTab
        }

        tabs.forEach { tab ->
            // İlk başta tüm tab'ları normal font yap
            tab.typeface = normalTypeface

            tab.setOnClickListener {
                if (selectedTab != it) {  // Aynı tab'a tekrar tıklanmayı önle
                    updateIndicator(it as TextView)
                    when (it.id) {
                        R.id.tabAll -> {
                            // All tab işlemleri
                        }
                        R.id.tabSeries -> {
                            // Series tab işlemleri
                        }
                        R.id.tabMovies -> {
                            // Movies tab işlemleri
                        }
                        R.id.tabLiveTV -> {
                            // Live TV tab işlemleri
                        }
                    }
                }
            }
        }

        // İlk tab'ı seçili olarak başlat
        updateIndicator(binding.tabAll)
    }
    private fun navigateToMovieDetail(topRatedId: String?) {
        val bundle = bundleOf("movieId" to topRatedId)
        findNavController().navigate(R.id.action_movieDetailsFragment_to_homeFragment, bundle)
    }

    private fun navigateMoviePlayerFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_playMovieFragment)
    }

    private fun navigateToTopRatedDetail(topRatedId: String?) {
        val bundle = bundleOf("movieId" to topRatedId)
        findNavController().navigate(R.id.action_homeFragment_to_topRatedDetailsFragment, bundle)
    }

    override fun onPause() {
        super.onPause()
        binding.shimmerLayout.stopShimmer()
    }
}
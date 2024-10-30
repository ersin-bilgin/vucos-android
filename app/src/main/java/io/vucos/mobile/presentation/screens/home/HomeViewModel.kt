package io.vucos.mobile.presentation.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.model.catalog.Widget
import io.vucos.mobile.domain.usecase.catalog.GetCatalogHome
import io.vucos.mobile.presentation.adapters.home.SlideItem
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.presentation.navigation_events.HomeNavigationEvent
import io.vucos.mobile.presentation.widgets.ChannelItem
import io.vucos.mobile.presentation.widgets.HomeWidget
import io.vucos.mobile.presentation.widgets.MovieItem
import io.vucos.mobile.presentation.widgets.ReplayItem
import io.vucos.mobile.presentation.widgets.TopRatedItem
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.TokenManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCatalogHome: GetCatalogHome,
    private val tokenManager: TokenManager,
    application: Application
) : BaseViewModel() {
    private val _widgets = MutableLiveData<List<HomeWidget>>()
    val widgets: LiveData<List<HomeWidget>> = _widgets

    private val _slideItems = MutableLiveData<List<SlideItem>>()
    val slideItems: LiveData<List<SlideItem>> = _slideItems

    private val _slideUrls = MutableLiveData<List<String>>()
    val slideUrls: LiveData<List<String>> = _slideUrls

    private val _movieTitles = MutableLiveData<List<String>>()
    val movieTitles: LiveData<List<String>> = _movieTitles

    private val _isShimmerLoading = MutableLiveData<Boolean>()
    val isShimmerLoading: LiveData<Boolean> = _isShimmerLoading

    private val _navigationEvent = MutableLiveData<HomeNavigationEvent>()
    val navigationEvent: LiveData<HomeNavigationEvent> = _navigationEvent

    private val _currentSlideIndex = MutableLiveData<Int>()
    val currentSlideIndex: LiveData<Int> = _currentSlideIndex

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private var autoScrollJob: kotlinx.coroutines.Job? = null

    init {
        fetchSlideItems()
    }

    fun fetchSlideItems() {
        viewModelScope.launch {
            try {
                _isShimmerLoading.value = true
                _error.value = null

                val getToken = tokenManager.getToken().toString()
                getCatalogHome("Bearer ${getToken}").collect { catalogResult ->
                    when (catalogResult) {
                        is Resource.Success -> {
                            try {
                                val slides = catalogResult.data.data.slides
                                val widgets = catalogResult.data.data.widgets

                                val items = slides.map { slide ->
                                    SlideItem(imageUrl = slide.mobileImageUrl, title = slide.name)
                                }
                                _slideItems.value = items
                                processWidgets(widgets)
                                startAutoScroll()

                                _isShimmerLoading.value = false
                            } catch (e: Exception) {
                                _error.value = "Veri işlenirken bir hata oluştu: ${e.message}"
                                _isShimmerLoading.value = false
                            }
                        }
                        is Resource.Error -> {
                            _error.value = catalogResult.message
                            _isShimmerLoading.value = false
                        }
                    }
                }
            } catch (e: Exception) {
                _error.value = "Veri alınırken bir hata oluştu: ${e.message}"
                _isShimmerLoading.value = false
            }
        }
    }

    fun retry() {
        fetchSlideItems()
    }

    fun onSlideChanged(position: Int) {
        _currentSlideIndex.value = position
        restartAutoScroll()
    }

    private fun processWidgets(apiWidgets: List<Widget>) {
        val widgets = apiWidgets.mapNotNull { apiWidget ->
            when (apiWidget.widgetType) {
                "CHANNEL_LIST" -> HomeWidget.ChannelListWidget(
                    title = apiWidget.title,
                    items = apiWidget.items.map { item ->
                        ChannelItem(
                            id = item.channelUId,
                            name = item.channelName,
                            logoUrl = item.channelPrimaryLogoImageUrl,
                            epgImageUrl = item.epgImageUrl
                        )
                    }
                )
                "REPLAYS" -> HomeWidget.ContinueToPlayWidget(
                    title = apiWidget.title,
                    items = apiWidget.items.map { item ->
                        ReplayItem(
                            id = item.channelUId,
                            title = item.title,
                            imageUrl = item.posterImageUrl,
                            pauseTime = item.pauseTime,
                            watchedPercent = item.watchedPercent,
                            duration = item.duration
                        )
                    }
                )
                "TOP_RATED" -> HomeWidget.TopRatedWidget(
                    title = apiWidget.title,
                    items = apiWidget.items.map { item ->
                        TopRatedItem(
                            contentUId = item.contentUId,
                            title = item.title,
                            posterImageUrl = item.posterImageUrl,
                            isInFavorites = item.isInFavorites,
                            isInWatchHistory = item.isInWatchHistory,
                        )
                    },
                    onItemClick = { topRatedItem -> onTopRatedItemClicked(topRatedItem) }
                )
                "VOD_CATEGORY" -> HomeWidget.MovieListWidget(
                    title = apiWidget.title,
                    items = apiWidget.items.map { item ->
                        MovieItem(
                            contentUId = item.contentUId,
                            title = item.title,
                            imageUrl = item.posterImageUrl
                        )
                    },
                    onItemClick = { movieDetailItem -> onMovieDetailItemClicked(movieDetailItem) }
                )
                else -> null
            }
        }
        _widgets.value = widgets
    }

    private fun onMovieDetailItemClicked(item: MovieItem) {
        Log.d("HomeViewModel", "Movie detail clicked: ${item.contentUId}")
        _navigationEvent.value = HomeNavigationEvent.ToMovieDetail(item.contentUId)
    }

    private fun onTopRatedItemClicked(item: TopRatedItem) {
        Log.d("HomeViewModel", "Top rated clicked: ${item.contentUId}")
        _navigationEvent.value = HomeNavigationEvent.ToTopRatedDetail(item.contentUId)
    }

    private fun startAutoScroll() {
        autoScrollJob?.cancel()
        autoScrollJob = viewModelScope.launch {
            while(true) {
                delay(3000)
                val currentIndex = currentSlideIndex.value ?: 0
                val slidesSize = slideItems.value?.size ?: 1
                if (slidesSize > 0) {
                    val nextIndex = (currentIndex + 1) % slidesSize
                    _currentSlideIndex.postValue(nextIndex)
                }
            }
        }
    }

    private fun restartAutoScroll() {
        autoScrollJob?.cancel()
        startAutoScroll()
    }

    override fun onCleared() {
        super.onCleared()
        autoScrollJob?.cancel()
    }
}
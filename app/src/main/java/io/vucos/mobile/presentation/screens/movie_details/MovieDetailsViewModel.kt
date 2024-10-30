package io.vucos.mobile.presentation.screens.movie_details

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.vucos.mobile.domain.model.recommendation.ContentItem
import io.vucos.mobile.domain.model.vod.detail.QualityDefinition
import io.vucos.mobile.domain.model.vod.detail.VodContentItem
import io.vucos.mobile.domain.usecase.recommendation.GetForVod
import io.vucos.mobile.domain.usecase.recommendation.GetForYou
import io.vucos.mobile.domain.usecase.vod.GetVodDetail
import io.vucos.mobile.presentation.base.BaseViewModel
import io.vucos.mobile.presentation.navigation_events.DetailsNavigationEvent
import io.vucos.mobile.util.Resource
import io.vucos.mobile.util.TokenManager
import io.vucos.mobile.util.VideoDurationCalculator
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getVodDetail: GetVodDetail,
    private val getForYou: GetForYou,
    private val getForVod: GetForVod,
    private val tokenManager: TokenManager,
    application: Application
) : BaseViewModel() {
    private val videoDurationCalculator = VideoDurationCalculator()
    private val _navigationEvent = MutableLiveData<DetailsNavigationEvent>()
    val navigationEvent: LiveData<DetailsNavigationEvent> = _navigationEvent

    private val _similarContent = MutableLiveData<List<ContentItem>>()
    val similarContent: LiveData<List<ContentItem>> = _similarContent

    private val _forYouContent = MutableLiveData<List<ContentItem>>()
    val forYouContent: LiveData<List<ContentItem>> = _forYouContent

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _episodesList = MutableLiveData<List<VodContentItem>?>()
    val episodesList: LiveData<List<VodContentItem>?> = _episodesList

    private val _seasonNumber = MutableLiveData<Int>()
    val seasonNumber: LiveData<Int> = _seasonNumber

    private val _platformRemoveDate = MutableLiveData<String>()
    val platformRemoveDate: LiveData<String> = _platformRemoveDate

    private val _isLiked = MutableLiveData<Boolean>()
    val isLiked: LiveData<Boolean> = _isLiked

    private val _isDisliked = MutableLiveData<Boolean>()
    val isDisliked: LiveData<Boolean> = _isDisliked

    private val _platformReleaseYear = MutableLiveData<Number>()
    val platformReleaseYear: LiveData<Number> = _platformReleaseYear

    private val _posterUrl = MutableLiveData<String>()
    val posterUrl: LiveData<String> = _posterUrl

    private val _originalTitle = MutableLiveData<String>()
    val originalTitle: LiveData<String> = _originalTitle

    private val _duration = MutableLiveData<String>()
    val duration: LiveData<String> = _duration

    private val _qualityDefinitions = MutableLiveData<List<QualityDefinition>>()
    val qualityDefinitions: LiveData<List<QualityDefinition>> = _qualityDefinitions

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _director = MutableLiveData<String>()
    val director: LiveData<String> = _director

    private val _cast = MutableLiveData<String>()
    val cast: LiveData<String> = _cast

    private val _deviceTypes = MutableLiveData<String>()
    val deviceTypes: LiveData<String> = _deviceTypes

    private val _isResumePlay = MutableLiveData<Boolean>()
    val isResumePlay: LiveData<Boolean> = _isResumePlay

    private val _watchProgress = MutableLiveData<Int>()
    val watchProgress: LiveData<Int> = _watchProgress

    private val _watchedDuration = MutableLiveData<String>()
    val watchedDuration: LiveData<String> = _watchedDuration

    private val _isPlus7Audience = MutableLiveData<Boolean>()
    val isPlus7Audience: LiveData<Boolean> = _isPlus7Audience

    private val _isPlus13Audience = MutableLiveData<Boolean>()
    val isPlus13Audience: LiveData<Boolean> = _isPlus13Audience

    private val _isPlus18Audience = MutableLiveData<Boolean>()
    val isPlus18Audience: LiveData<Boolean> = _isPlus18Audience

    private val _isViolenceAndFearAudience = MutableLiveData<Boolean>()
    val isViolenceAndFearAudience: LiveData<Boolean> = _isViolenceAndFearAudience

    private val _isAdultAudience = MutableLiveData<Boolean>()
    val isAdultAudience: LiveData<Boolean> = _isAdultAudience

    private val _averageLikes = MutableLiveData<String>()
    val averageLikes: LiveData<String> = _averageLikes

    private val _averageDislikes = MutableLiveData<String>()
    val averageDislikes: LiveData<String> = _averageDislikes

    private val _isNegativityAudience = MutableLiveData<Boolean>()
    val isNegativityAudience: LiveData<Boolean> = _isNegativityAudience

    private val _isGeneralAudience = MutableLiveData<Boolean>()
    val isGeneralAudience: LiveData<Boolean> = _isGeneralAudience

    private val _remainingTime = MutableLiveData<String>()
    val remainingTime: LiveData<String> = _remainingTime

    private var vodUId: String? = null
    private var vodDetails: List<VodContentItem>? = null

    fun setVodUId(id: String?) {
        vodUId = id
    }

    fun navigateToHome() {
        _navigationEvent.value = DetailsNavigationEvent.NavigateToHome
    }

    fun getTabContent(tabIndex: Int) {
        viewModelScope.launch {
            try {
                setLoading(true)
                val token = tokenManager.getToken().toString()
                when (tabIndex) {
                    0 -> getSimilarContent("Bearer $token", vodUId)
                    1 -> getForYouContent("Bearer $token", vodUId)
                }
            } catch (e: Exception) {
                Log.e("MovieDetailsViewModel", "Sekme içeriği yüklenirken hata oluştu: ${e.message}", e)
            } finally {
                setLoading(false)
            }
        }
    }

    private suspend fun getSimilarContent(token: String, vodUid: String?) {
        getForVod(token, vodUid).collect { result ->
            when (result) {
                is Resource.Success -> _similarContent.postValue(result.data.data)
                is Resource.Error -> Log.e("MovieDetailsViewModel", "Benzer içerik yüklenirken hata oluştu: ${result.message}")
            }
        }
    }

    private suspend fun getForYouContent(token: String, vodUid: String?) {
        getForYou(token, vodUid).collect { result ->
            when (result) {
                is Resource.Success -> _forYouContent.postValue(result.data.data)
                is Resource.Error -> Log.e("MovieDetailsViewModel", "Size özel içerik yüklenirken hata oluştu: ${result.message}")
            }
        }
    }

    fun getVodDetails(movieId: String?) {
        viewModelScope.launch {
            setLoading(true)
            val token = tokenManager.getToken().toString()
            getVodDetail("Bearer $token", movieId).collect { vodDetailResult ->
                when (vodDetailResult) {
                    is Resource.Success -> {
                        val contentItems = vodDetailResult.data.data
                        if (contentItems.isNullOrEmpty()) {
                            Log.e("MovieDetailsViewModel", "İçerik öğeleri boş veya null")
                            setLoading(false)
                            return@collect
                        }
                        vodDetails = contentItems
                        val firstItem = contentItems.firstOrNull()
                        if (firstItem == null) {
                            Log.e("MovieDetailsViewModel", "İlk içerik öğesi null")
                            setLoading(false)
                            return@collect
                        }

                        _title.value = firstItem.title
                        _description.value = firstItem.description
                        _platformRemoveDate.value = firstItem.licenseEnd
                        _platformReleaseYear.value = firstItem.releaseYear
                        _posterUrl.value = firstItem.posters.firstOrNull()?.imageUrl
                        _originalTitle.value = firstItem.originalTitle

                        val durationInSeconds = firstItem.duration?.toInt() ?: 0
                        _duration.value = videoDurationCalculator.calculateVideoDuration(durationInSeconds)

                        _qualityDefinitions.value = firstItem.qualityDefinitions.map {
                            QualityDefinition(it.name, it.logoUrl)
                        }

                        val availableDevices = firstItem.deviceAvailability
                            .filter { it.isAvailable && it.isWatchAvailable }
                            .joinToString(separator = ", ") { it.deviceTypeName }
                        _deviceTypes.value = if (availableDevices.isNotEmpty()) {
                            "Platform: $availableDevices"
                        } else {
                            ""
                        }

                        _averageLikes.value = "%${firstItem.averageLikes}"
                        _averageDislikes.value = "%${firstItem.averageDislikes}"
                        _isAdultAudience.value = firstItem.isAdultAudience
                        _isNegativityAudience.value = firstItem.isNegativityAudience
                        _isPlus7Audience.value = firstItem.isPlus7Audience
                        _isPlus13Audience.value = firstItem.isPlus13Audience
                        _isPlus18Audience.value = firstItem.isPlus18Audience
                        _isGeneralAudience.value = firstItem.isGeneralAudience

                        val totalDurationInSeconds = firstItem.duration ?: 0
                        val pausedTimeInSeconds = firstItem.pausedTime ?: 0
                        val watchedPercent = if (totalDurationInSeconds > 0) {
                            (pausedTimeInSeconds.toDouble() / totalDurationInSeconds) * 100
                        } else {
                            0.0
                        }
                        _watchProgress.value = watchedPercent.toInt()

                        _seasonNumber.value = vodDetails?.firstOrNull()?.seasonNumber ?: 0

                        val remainingTimeInSeconds = totalDurationInSeconds - pausedTimeInSeconds
                        _remainingTime.value = videoDurationCalculator.calculateVideoDuration(remainingTimeInSeconds)
                        _isResumePlay.value = pausedTimeInSeconds > 0

                        val watchedTimeInSeconds = videoDurationCalculator.calculateWatchedDuration(watchedPercent, totalDurationInSeconds)
                        _watchedDuration.value = watchedTimeInSeconds

                        val castList = firstItem.cast
                        _director.value = castList.find { it.type == "DIRECTOR" }?.name ?: "Bilinmiyor"
                        _cast.value = castList.filter { it.type == "ACTOR" }
                            .joinToString(", ") { it.name }

                        updateEpisodesList()
                        setLoading(false)
                    }
                    is Resource.Error -> {
                        Log.e("MovieDetailsViewModel", "Veri yüklenirken hata oluştu: ${vodDetailResult.message}")
                        setLoading(false)
                    }
                    else -> {
                        Log.e("MovieDetailsViewModel", "Beklenmeyen bir sonuç alındı")
                        setLoading(false)
                    }
                }
            }
        }
    }

    fun onLikeClicked() {
        viewModelScope.launch {
            setLoading(true)
            val token = tokenManager.getToken().toString()
            // Beğenme işlemi burada uygulanacak
            setLoading(false)
        }
    }

    fun onDislikeClicked() {
        viewModelScope.launch {
            setLoading(true)
            val token = tokenManager.getToken().toString()
            // Beğenmeme işlemi burada uygulanacak
            setLoading(false)
        }
    }

    private fun updateEpisodesList() {
        val seasonNumber = _seasonNumber.value ?: 0
        if (seasonNumber > 0 && !vodDetails.isNullOrEmpty()) {
            _episodesList.value = vodDetails
            Log.d("MovieDetailsViewModel", "Bölümler yüklendi: ${_episodesList.value?.size}")
        } else {
            _episodesList.value = emptyList()
            Log.d("MovieDetailsViewModel", "Bölüm yüklenemedi. Sezon numarası: $seasonNumber, VodDetails boş mu: ${vodDetails.isNullOrEmpty()}")
        }
    }
}
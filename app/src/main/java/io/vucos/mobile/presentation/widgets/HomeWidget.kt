package io.vucos.mobile.presentation.widgets

sealed class HomeWidget {
    data class ChannelListWidget(
        val title: String,
        val items: List<ChannelItem>
    ) : HomeWidget()

    data class ContinueToPlayWidget(
        val title: String,
        val items: List<ReplayItem>
    ) : HomeWidget()

    data class MovieListWidget(
        val title: String,
        val items: List<MovieItem>,
        val onItemClick: (MovieItem) -> Unit
    ) : HomeWidget()

    data class TopRatedWidget(
        val title: String,
        val items: List<TopRatedItem>,
        val onItemClick: (TopRatedItem) -> Unit
    ) : HomeWidget()
}

data class ChannelItem(
    val id: String?,
    val name: String?,
    val logoUrl: String?,
    val epgImageUrl: String?,
)

data class ReplayItem(
    val id: String?,
    val title: String?,
    val imageUrl: String?,
    val pauseTime: Int?,
    val duration: Int,
    val watchedPercent: Double
)

data class TopRatedItem(
    val contentUId: String?,
    val title: String?,
    val posterImageUrl: String?,
    val isInFavorites: Boolean?,
    val isInWatchHistory: Boolean?
)

data class MovieItem(
    val contentUId: String?,
    val title: String?,
    val imageUrl: String?
)
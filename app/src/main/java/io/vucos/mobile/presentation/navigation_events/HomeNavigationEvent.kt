package io.vucos.mobile.presentation.navigation_events

sealed class HomeNavigationEvent {
    data class ToMovieDetail(val movieId: String?) : HomeNavigationEvent()
    data class ToTopRatedDetail(val movieId: String?) : HomeNavigationEvent()
    // Diğer olası navigasyon eventleri buraya eklenebilir
}
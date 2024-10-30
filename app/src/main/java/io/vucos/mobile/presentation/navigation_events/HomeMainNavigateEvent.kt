package io.vucos.mobile.presentation.navigation_events

sealed class HomeMainNavigationEvent {
    object NavigateToMain : HomeMainNavigationEvent()
    object NavigateBack : HomeMainNavigationEvent()
    object NavigateToLoading : HomeMainNavigationEvent()
    object NavigateToWhoWillWatch : HomeMainNavigationEvent()
    object NavigateToMainContent : HomeMainNavigationEvent()
}
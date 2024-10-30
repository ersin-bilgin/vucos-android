package io.vucos.mobile.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import io.vucos.mobile.R
import kotlinx.coroutines.delay

suspend fun Fragment.navigateWithLoading(
    loadingFragmentId: Int,
    destinationId: Int,
    loadingTime: Long = 4000,
) {
    findNavController().navigate(loadingFragmentId)

    // Belirtilen süre kadar bekle
    delay(loadingTime)

    // Callback'i çağır, LoadingFragment'tan dönen Fragment bu callback'i işleyecek
}
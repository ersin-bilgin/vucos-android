package io.vucos.mobile.util

class VideoDurationCalculator {

    fun calculateVideoDuration(duration: Int): String {
        val hours = duration / 3600
        val minutes = (duration % 3600) / 60
        val seconds = duration % 60
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    fun calculateWatchedDuration(watchedPercent: Double, duration: Int): String {
        val watchedTime = (duration * (watchedPercent / 100)).toInt()
        val hours = watchedTime / 3600
        val minutes = (watchedTime % 3600) / 60
        val seconds = watchedTime % 60
        return "%02d:%02d:%02d".format(hours, minutes, seconds)
    }

    fun formatDuration(videoDuration: String, watchedDuration: String): String {
        return "$watchedDuration / $videoDuration"
    }

    fun calculateWatchedPercent(watchedPercent: Double): Double {
        return watchedPercent
    }
}
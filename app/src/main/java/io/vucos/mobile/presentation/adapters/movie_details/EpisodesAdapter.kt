package io.vucos.mobile.presentation.adapters.movie_details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.vucos.mobile.databinding.ItemEpisodeBinding
import io.vucos.mobile.domain.model.vod.detail.VodContentItem

class EpisodesAdapter : ListAdapter<VodContentItem, EpisodesAdapter.EpisodeViewHolder>(EpisodeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding = ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = getItem(position)
        holder.bind(episode)
    }

    class EpisodeViewHolder(private val binding: ItemEpisodeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(episode: VodContentItem) {
            binding.episodeTitle.text = episode.title
            binding.episodeDescription.text = episode.description
            binding.episodeNumber.text = "Bölüm ${episode.episodeNumber}"
            binding.episodeDuration.text = formatDuration(episode.duration ?: 0)

            Log.d("EpisodesAdapter", "Binding episode: ${episode.title}")

            // Filter and get only the PREVIEW poster
            val previewPoster = episode.posters.find { it.type == "DETAIL" }

            if (previewPoster != null) {
                Log.d("EpisodesAdapter", "Loading preview image: ${previewPoster.imageUrl}")

                Glide.with(binding.root.context)
                    .load(previewPoster.imageUrl)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(binding.episodePreviewImage)

                binding.episodePreviewImage.visibility = android.view.View.VISIBLE
            } else {
                Log.d("EpisodesAdapter", "No preview image found for episode: ${episode.title}")
                binding.episodePreviewImage.visibility = android.view.View.GONE
            }
        }

        private fun formatDuration(durationInSeconds: Int): String {
            val hours = durationInSeconds / 3600
            val minutes = (durationInSeconds % 3600) / 60
            return if (hours > 0) {
                String.format("%d sa %02d dk", hours, minutes)
            } else {
                String.format("%d dk", minutes)
            }
        }
    }

    class EpisodeDiffCallback : DiffUtil.ItemCallback<VodContentItem>() {
        override fun areItemsTheSame(oldItem: VodContentItem, newItem: VodContentItem): Boolean {
            return oldItem.uId == newItem.uId
        }

        override fun areContentsTheSame(oldItem: VodContentItem, newItem: VodContentItem): Boolean {
            return oldItem == newItem
        }
    }
}
// EpisodeThumbnailPagerAdapter için yeni veri sınıfı
data class PosterItem(val imageUrl: String, val type: String)
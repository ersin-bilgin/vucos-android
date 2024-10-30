package io.vucos.mobile.presentation.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vucos.mobile.R
import io.vucos.mobile.presentation.widgets.ReplayItem
import io.vucos.mobile.util.VideoDurationCalculator

class ReplayAdapter : ListAdapter<ReplayItem, ReplayAdapter.ReplayViewHolder>(ReplayDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplayViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_continue_to_play, parent, false)
        return ReplayViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReplayViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ReplayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.contentTitle)
        private val durationTextView: TextView = itemView.findViewById(R.id.duration)
        private val imageView: ImageView = itemView.findViewById(R.id.contentThumbnail)
        private val ProgressBar: ProgressBar = itemView.findViewById(R.id.watchProgress)


        fun bind(item: ReplayItem) {
            val calculator = VideoDurationCalculator()
            val videoDuration = calculator.calculateVideoDuration(item.duration)
            val watchedDuration = calculator.calculateWatchedDuration(item.watchedPercent, item.duration)
            val formattedDuration = calculator.formatDuration(videoDuration, watchedDuration)


            titleTextView.text = item.title
            durationTextView.text = formattedDuration
            Glide.with(itemView.context).load(item.imageUrl).into(imageView)
            ProgressBar.progress = item.watchedPercent.toInt()
            // Load image using your preferred image loading library (e.g., Glide, Picasso)
            // Example using Glide:
            // Glide.with(itemView.context).load(item.imageUrl).into(imageView)


        }
    }

    class ReplayDiffCallback : DiffUtil.ItemCallback<ReplayItem>() {
        override fun areItemsTheSame(oldItem: ReplayItem, newItem: ReplayItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReplayItem, newItem: ReplayItem): Boolean {
            return oldItem == newItem
        }
    }
}
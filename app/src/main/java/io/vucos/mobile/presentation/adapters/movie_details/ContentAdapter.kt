package io.vucos.mobile.presentation.adapters.movie_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vucos.mobile.R
import io.vucos.mobile.domain.model.recommendation.ContentItem

class ContentAdapter(private val onItemClick: (ContentItem) -> Unit) : ListAdapter<ContentItem, ContentAdapter.ContentViewHolder>(ContentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content_grid, parent, false)
        return ContentViewHolder(view,onItemClick)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val content = getItem(position)
        holder.bind(content)
    }

    class ContentViewHolder(
        itemView: View,
        private val onItemClick: (ContentItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.contentImageView)

        fun bind(content: ContentItem) {
            Glide.with(itemView.context)
                .load(content.posterImageUrl)
                .into(imageView)

            itemView.setOnClickListener { onItemClick(content) }
        }
    }

    class ContentDiffCallback : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem.contentUId == newItem.contentUId
        }

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            return oldItem == newItem
        }
    }
}
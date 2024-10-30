package io.vucos.mobile.presentation.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vucos.mobile.R
import io.vucos.mobile.presentation.widgets.TopRatedItem


class TopRatedAdapter(
    private val onItemClick: (TopRatedItem) -> Unit
) : ListAdapter<TopRatedItem, TopRatedAdapter.TopRatedViewHolder>(TopRatedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_top_rated, parent, false)
        return TopRatedViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: TopRatedViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TopRatedViewHolder(
        itemView: View,
        private val onItemClick: (TopRatedItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.topRatedMoviePoster)

        fun bind(item: TopRatedItem) {
            Glide.with(itemView.context).load(item.posterImageUrl).into(imageView)

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class TopRatedDiffCallback : DiffUtil.ItemCallback<TopRatedItem>() {
        override fun areItemsTheSame(oldItem: TopRatedItem, newItem: TopRatedItem): Boolean {
            return oldItem.contentUId == newItem.contentUId
        }

        override fun areContentsTheSame(oldItem: TopRatedItem, newItem: TopRatedItem): Boolean {
            return oldItem == newItem
        }
    }
}
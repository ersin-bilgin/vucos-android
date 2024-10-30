package io.vucos.mobile.presentation.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.vucos.mobile.R
import io.vucos.mobile.presentation.widgets.ChannelItem

class ChannelAdapter : ListAdapter<ChannelItem, ChannelAdapter.ChannelViewHolder>(ChannelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ChannelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //private val nameTextView: TextView = itemView.findViewById(R.id.channelName)
        private val logoImageView: ImageView = itemView.findViewById(R.id.channelLogo)
        private val logoScondary: ImageView = itemView.findViewById(R.id.epgImageUrl)


        fun bind(item: ChannelItem) {
            //nameTextView.text = item.name
            Glide.with(itemView.context).load(item.logoUrl).into(logoImageView)
            Glide.with(itemView.context).load(item.epgImageUrl).into(logoScondary)
            // Load logo image using your preferred image loading library (e.g., Glide, Picasso)
            // Example using Glide:
            // Glide.with(itemView.context).load(item.logoUrl).into(logoImageView)
        }
    }

    class ChannelDiffCallback : DiffUtil.ItemCallback<ChannelItem>() {
        override fun areItemsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ChannelItem, newItem: ChannelItem): Boolean {
            return oldItem == newItem
        }
    }
}
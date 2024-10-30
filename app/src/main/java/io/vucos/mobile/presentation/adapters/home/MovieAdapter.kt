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
import io.vucos.mobile.presentation.widgets.MovieItem

class MovieAdapter(private val onItemClick: (MovieItem) -> Unit) : ListAdapter<MovieItem, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView, onItemClick)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MovieViewHolder(itemView: View, private val onItemClick: (MovieItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.moviePoster)

        fun bind(item: MovieItem) {
            Glide.with(itemView.context).load(item.imageUrl).into(imageView)
            // Load image using your preferred image loading library (e.g., Glide, Picasso)
            // Example using Glide:
            // Glide.with(itemView.context).load(item.imageUrl).into(imageView)
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.contentUId == newItem.contentUId
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem == newItem
        }
    }
}
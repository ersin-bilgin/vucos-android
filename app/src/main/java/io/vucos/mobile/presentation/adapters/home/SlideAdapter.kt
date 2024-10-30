package io.vucos.mobile.presentation.adapters.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.vucos.mobile.databinding.ItemHomeSlideBinding

data class SlideItem(val imageUrl: String, val title: String)

class SlideAdapter(
    private val onButtonClick: (Int) -> Unit,
    private val onImageLoaded: (Bitmap) -> Unit
) : ListAdapter<SlideItem, SlideAdapter.SlideViewHolder>(SlideDiffCallback()) {

    private val bitmapCache = mutableMapOf<Int, Bitmap>()

    fun getCurrentBitmap(position: Int): Bitmap? = bitmapCache[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val binding = ItemHomeSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.slidePlayButton.setOnClickListener {
            Log.d("SlideAdapter","PLAY BUTTON CLICK")
            onButtonClick(0)
        }

        binding.slideAddMyListButton.setOnClickListener {
            Log.d("SlideAdapter","Add My List BUTTON CLICK")
            onButtonClick(1)
        }

        return SlideViewHolder(binding, onButtonClick) { position, bitmap ->
            bitmapCache[position] = bitmap
            onImageLoaded(bitmap)
        }
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SlideViewHolder(
        private val binding: ItemHomeSlideBinding,
        private val onButtonClick: (Int) -> Unit,
        private val onImageLoaded: (Int, Bitmap) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SlideItem) {
            Glide.with(binding.root.context)
                .asBitmap()
                .load(item.imageUrl)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        binding.slideImage.setImageBitmap(resource)
                        onImageLoaded(bindingAdapterPosition, resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        binding.slideImage.setImageDrawable(placeholder)
                    }
                })

            binding.movieTitle.apply {
                text = item.title.takeIf { it.isNotBlank() } ?: "Başlık Yok"
                isVisible = item.title.isNotBlank()
                setOnClickListener {
                    // Film başlığına tıklandığında yapılacak işlemler
                }
            }

            binding.slidePlayButton.setOnClickListener { onButtonClick(0) }
            binding.slideAddMyListButton.setOnClickListener { onButtonClick(1) }

        }
    }

    class SlideDiffCallback : DiffUtil.ItemCallback<SlideItem>() {
        override fun areItemsTheSame(oldItem: SlideItem, newItem: SlideItem): Boolean =
            oldItem.imageUrl == newItem.imageUrl

        override fun areContentsTheSame(oldItem: SlideItem, newItem: SlideItem): Boolean =
            oldItem == newItem
    }
}
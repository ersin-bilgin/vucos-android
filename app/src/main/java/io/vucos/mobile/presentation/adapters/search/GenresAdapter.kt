package io.vucos.mobile.presentation.adapters.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.vucos.mobile.databinding.GenreItemBinding
import io.vucos.mobile.domain.model.resources.genres.GenresResponse

class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {

    private var genres = listOf<GenresResponse>()

    fun submitList(newList: List<GenresResponse>) {
        genres = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size

    inner class GenreViewHolder(
        private val binding: GenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: GenresResponse) {
            binding.apply {
                this.genre = genre
                executePendingBindings()
            }
        }
    }
}
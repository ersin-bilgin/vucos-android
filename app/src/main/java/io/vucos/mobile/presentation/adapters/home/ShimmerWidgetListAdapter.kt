package io.vucos.mobile.presentation.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.vucos.mobile.R
import io.vucos.mobile.databinding.ShimmerWidgetListItemBinding

class ShimmerWidgetListAdapter : RecyclerView.Adapter<ShimmerWidgetListAdapter.ShimmerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        val binding = ShimmerWidgetListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ShimmerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        // Shimmer effect için bir şey yapmamıza gerek yok
    }

    override fun getItemCount(): Int = 5 // Sabit 5 item gösteriyoruz

    class ShimmerViewHolder(binding: ShimmerWidgetListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
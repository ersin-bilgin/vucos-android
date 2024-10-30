package io.vucos.mobile.presentation.adapters.who_will_watch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.vucos.mobile.R
import io.vucos.mobile.databinding.ItemAddProfileButtonBinding
import io.vucos.mobile.databinding.ItemWhoWillWatchButtonBinding
import io.vucos.mobile.domain.model.profiles.Profile

class WhoWillWatchAdapter(
    private val onProfileClicked: (Profile) -> Unit,
    private val onAddProfileClick: () -> Unit
) : ListAdapter<Profile, RecyclerView.ViewHolder>(DiffCallback) {

    private val VIEW_TYPE_PROFILE = 0
    private val VIEW_TYPE_ADD_PROFILE = 1

    override fun getItemCount(): Int = super.getItemCount() + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == currentList.size) VIEW_TYPE_ADD_PROFILE else VIEW_TYPE_PROFILE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PROFILE -> {
                ProfileViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_who_will_watch_button,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_ADD_PROFILE -> {
                AddProfileViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_add_profile_button,
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProfileViewHolder -> {
                val profile = getItem(position)
                holder.bind(profile)
            }
            is AddProfileViewHolder -> {
                holder.bind()
            }
        }
    }

    inner class ProfileViewHolder(private val binding: ItemWhoWillWatchButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.apply {
                this.profile = profile
                root.setOnClickListener { onProfileClicked(profile) }
                executePendingBindings()
            }
        }
    }

    inner class AddProfileViewHolder(private val binding: ItemAddProfileButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.root.setOnClickListener { onAddProfileClick() }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean =
            oldItem.uId == newItem.uId

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean =
            oldItem == newItem
    }
}
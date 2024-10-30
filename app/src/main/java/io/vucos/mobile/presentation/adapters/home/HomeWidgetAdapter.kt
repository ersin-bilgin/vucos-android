package io.vucos.mobile.presentation.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.vucos.mobile.R
import io.vucos.mobile.presentation.widgets.HomeWidget

class HomeWidgetAdapter : ListAdapter<HomeWidget, RecyclerView.ViewHolder>(HomeWidgetDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_CHANNEL_LIST -> ChannelListViewHolder(inflater.inflate(R.layout.item_channel_list_widget, parent, false))
            VIEW_TYPE_CONTINUE_TO_PLAY -> ContinueToPlayViewHolder(inflater.inflate(R.layout.item_continue_to_play_widget, parent, false))
            VIEW_TYPE_MOVIE_LIST -> MovieListViewHolder(inflater.inflate(R.layout.item_movie_list_widget, parent, false))
            VIEW_TYPE_TOP_RATED -> TopRatedListViewHolder(inflater.inflate(R.layout.item_top_rated_widget, parent, false))
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val widget = getItem(position)) {
            is HomeWidget.ChannelListWidget -> (holder as ChannelListViewHolder).bind(widget)
            is HomeWidget.ContinueToPlayWidget -> (holder as ContinueToPlayViewHolder).bind(widget)
            is HomeWidget.MovieListWidget -> (holder as MovieListViewHolder).bind(widget)
            is HomeWidget.TopRatedWidget -> (holder as TopRatedListViewHolder).bind(widget)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is HomeWidget.ChannelListWidget -> VIEW_TYPE_CHANNEL_LIST
            is HomeWidget.ContinueToPlayWidget -> VIEW_TYPE_CONTINUE_TO_PLAY
            is HomeWidget.MovieListWidget -> VIEW_TYPE_MOVIE_LIST
            is HomeWidget.TopRatedWidget -> VIEW_TYPE_TOP_RATED
        }
    }

    class ChannelListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.widgetTitle)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.channelRecyclerView)
        private val channelAdapter = ChannelAdapter()

        init {
            recyclerView.adapter = channelAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(widget: HomeWidget.ChannelListWidget) {
            titleTextView.text = widget.title
            channelAdapter.submitList(widget.items)
        }
    }

    class ContinueToPlayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.widgetTitle)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.continueToPlayRecyclerView)
        private val replayAdapter = ReplayAdapter()

        init {
            recyclerView.adapter = replayAdapter
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bind(widget: HomeWidget.ContinueToPlayWidget) {
            titleTextView.text = widget.title
            replayAdapter.submitList(widget.items)
        }
    }

    class TopRatedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.widgetTitle)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.topRatedRecyclerView)
        private lateinit var topRatedAdapter: TopRatedAdapter

        fun bind(widget: HomeWidget.TopRatedWidget) {
            titleTextView.text = widget.title
            if (!::topRatedAdapter.isInitialized) {
                topRatedAdapter = TopRatedAdapter(widget.onItemClick)
                recyclerView.adapter = topRatedAdapter
                recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
            topRatedAdapter.submitList(widget.items)
        }
    }

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.widgetTitle)
        private val recyclerView: RecyclerView = itemView.findViewById(R.id.movieRecyclerView)
        private lateinit var movieAdapter: MovieAdapter

        fun bind(widget: HomeWidget.MovieListWidget) {
            titleTextView.text = widget.title
            if (!::movieAdapter.isInitialized) {
                movieAdapter = MovieAdapter(widget.onItemClick)
                recyclerView.adapter = movieAdapter
                recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
            movieAdapter.submitList(widget.items)
        }
    }

    companion object {
        private const val VIEW_TYPE_CHANNEL_LIST = 0
        private const val VIEW_TYPE_CONTINUE_TO_PLAY = 1
        private const val VIEW_TYPE_MOVIE_LIST = 2
        private const val VIEW_TYPE_TOP_RATED = 3
    }

    class HomeWidgetDiffCallback : DiffUtil.ItemCallback<HomeWidget>() {
        override fun areItemsTheSame(oldItem: HomeWidget, newItem: HomeWidget): Boolean {
            return oldItem.javaClass == newItem.javaClass &&
                    when {
                        oldItem is HomeWidget.ChannelListWidget && newItem is HomeWidget.ChannelListWidget -> oldItem.title == newItem.title
                        oldItem is HomeWidget.ContinueToPlayWidget && newItem is HomeWidget.ContinueToPlayWidget -> oldItem.title == newItem.title
                        oldItem is HomeWidget.MovieListWidget && newItem is HomeWidget.MovieListWidget -> oldItem.title == newItem.title
                        oldItem is HomeWidget.TopRatedWidget && newItem is HomeWidget.TopRatedWidget -> oldItem.title == newItem.title
                        else -> false
                    }
        }

        override fun areContentsTheSame(oldItem: HomeWidget, newItem: HomeWidget): Boolean {
            return oldItem == newItem
        }
    }
}
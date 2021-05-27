package com.example.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Media
import com.example.core.util.ImageUtil
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ComponentCatalogueBinding

class FavoriteAdapter(private val clickListener: (Int) -> Unit) :
    ListAdapter<Media, FavoriteAdapter.ViewHolder>(FavoriteDiffCallback()) {
    class ViewHolder(
        private val binding: ComponentCatalogueBinding,
        private val clickListener: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Media) {
            binding.apply {
                catalogueRootCv.setOnClickListener {
                    clickListener.invoke(item.id)
                }
                ImageUtil.loadImage(
                    item.imageUrl,
                    R.drawable.ic_default_movie,
                    catalogueImageIv
                )
                catalogueTitleTv.text = item.title
                catalogueFavoriteIv.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComponentCatalogueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }
}

class FavoriteDiffCallback : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Media, newItem: Media): Boolean {
        return oldItem == newItem
    }
}

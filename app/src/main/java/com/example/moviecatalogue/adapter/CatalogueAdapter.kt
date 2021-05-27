package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.domain.model.Media
import com.example.core.util.ImageUtil.loadImage
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.ComponentCatalogueBinding

class CatalogueAdapter(
    private val clickListener: (Int) -> Unit
) :
    ListAdapter<Media, CatalogueAdapter.ViewHolder>(MediaDiffCallback()) {
    inner class ViewHolder(
        private val binding: ComponentCatalogueBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Media) {
            binding.apply {
                catalogueRootCv.setOnClickListener {
                    clickListener.invoke(item.id)
                }
                loadImage(item.imageUrl, R.drawable.ic_default_movie, catalogueImageIv)
                catalogueTitleTv.text = item.title
                catalogueFavoriteIv.isVisible = item.isFavorite
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComponentCatalogueBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }
}

class MediaDiffCallback : DiffUtil.ItemCallback<Media>() {
    override fun areItemsTheSame(
        oldItem: Media,
        newItem: Media,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Media,
        newItem: Media,
    ): Boolean {
        return oldItem == newItem
    }
}

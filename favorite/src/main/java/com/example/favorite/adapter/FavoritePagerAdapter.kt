package com.example.favorite.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.core.domain.model.MediaFormat
import com.example.core.util.EXTRA_MEDIA_FORMAT
import com.example.favorite.ui.FavoriteTabFragment

class FavoritePagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteTabFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_MEDIA_FORMAT, MediaFormat.TV)
                }
            }
            else -> FavoriteTabFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_MEDIA_FORMAT, MediaFormat.MOVIE)
                }
            }
        }
    }
}
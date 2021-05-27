package com.example.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.util.DataState
import com.example.favorite.adapter.FavoritePagerAdapter
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.viewModelModule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.core.context.loadKoinModules

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {
    override fun runOnCreateView() {
        super.runOnCreateView()
        showResult(DataState.EXISTS)
        binding.apply {
            val favoritePagerAdapter =
                FavoritePagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
            favoriteViewVp2.isUserInputEnabled = false
            favoriteViewVp2.adapter = favoritePagerAdapter
            (favoriteViewVp2.getChildAt(0) as ViewGroup).clipChildren = false
            TabLayoutMediator(favoriteTabsTl, favoriteViewVp2) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.tv_show_tab)
                    1 -> tab.text = getString(R.string.movie_tab)
                }
            }.attach()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadKoinModules(viewModelModule)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
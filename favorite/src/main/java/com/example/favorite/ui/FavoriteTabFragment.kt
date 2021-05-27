package com.example.favorite.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.model.MediaFormat
import com.example.core.util.DataState
import com.example.core.util.EXTRA_MEDIA_FORMAT
import com.example.favorite.adapter.FavoriteAdapter
import com.example.favorite.databinding.FragmentFavoriteTabBinding
import com.example.favorite.viewmodel.FavoriteViewModel
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTabFragment :
    BaseFragment<FragmentFavoriteTabBinding>(FragmentFavoriteTabBinding::inflate) {
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var format: MediaFormat

    override fun runOnCreateView() {
        super.runOnCreateView()
        binding.apply {
            val favoriteAdapter = FavoriteAdapter(::navigateToDetail)
            favoriteTabListRv.apply {
                adapter = favoriteAdapter
                layoutManager = GridLayoutManager(mContext, 2)
            }
            viewModel.medias.observe(viewLifecycleOwner, {
                if (it.isEmpty()) {
                    showResult(
                        DataState.NOT_FOUND,
                        getString(R.string.nothing_here)
                    )
                } else {
                    favoriteAdapter.submitList(it)
                    showResult(DataState.EXISTS)
                }
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        format = arguments?.getSerializable(EXTRA_MEDIA_FORMAT) as MediaFormat
        viewModel.getMediasFavorite(format)
    }

    override fun onDestroyView() {
        binding.favoriteTabListRv.adapter = null
        super.onDestroyView()
    }

    private fun navigateToDetail(mediaId: Int) {
        val destination =
            FavoriteFragmentDirections.actionFavoriteFragmentToCatalogueDetailFragment(mediaId)
        findNavController().navigate(destination)
    }

    override fun getRootViewGroup(): ViewGroup = binding.root
}
package com.example.moviecatalogue.ui

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.example.core.domain.model.Media
import com.example.core.util.DataState
import com.example.core.util.HtmlUtil
import com.example.core.util.ImageUtil.loadImage
import com.example.core.util.getOrDefault
import com.example.core.util.plus
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentCatalogueDetailBinding
import com.example.moviecatalogue.util.CatalogueUtil.getEmoResourceId
import com.example.moviecatalogue.viewmodel.CatalogueDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CatalogueDetailFragment :
    BaseFragment<FragmentCatalogueDetailBinding>(FragmentCatalogueDetailBinding::inflate) {
    private val viewModel: CatalogueDetailViewModel by viewModel()
    private val args: CatalogueDetailFragmentArgs by this.navArgs()

    override fun runOnCreateView() {
        super.runOnCreateView()
        binding.apply {
            catalogueDetailFavoriteFab.setOnClickListener {
                viewModel.toggleFavorite()
            }
            viewModel.media.observe(viewLifecycleOwner, {
                updateLayout(it)
                showResult(DataState.EXISTS)
            })
        }
    }

    private fun changeFavoriteIconAndText(favorites: Int, isFavorite: Boolean) {
        val iconId =
            if (isFavorite) R.drawable.ic_favorite_black else R.drawable.ic_favorite_border_white
        binding.apply {
            catalogueDetailFavoriteFab.setImageResource(iconId)
            catalogueDetailFavoriteTv.text =
                favorites.plus(isFavorite)
                    .getOrDefault(mContext, true)
        }
    }

    private fun updateLayout(item: Media) {
        binding.apply {
            loadImage(
                item.imageUrl,
                R.drawable.ic_default_movie,
                catalogueDetailImageSiv
            )
            loadImage(
                item.bannerImageUrl,
                R.drawable.ic_default_movie,
                catalogueDetailBannerSiv
            )
            catalogueDetailTitleTv.text =
                item.title.getOrDefault(mContext)
            catalogueDetailDescriptionTv.text =
                HtmlUtil.fromHtml(item.description.getOrDefault(mContext))
            catalogueDetailScoreTv.text = String.format(
                getString(R.string.score_value),
                item.averageScore.getOrDefault(mContext)
            )
            catalogueDetailScoreTv.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0,
                0,
                getEmoResourceId(item.averageScore),
                0
            )
            changeFavoriteIconAndText(item.favorites, item.isFavorite)
            catalogueDetailEpisodeTv.text = item.episodes.getOrDefault(mContext)
            catalogueDetailDurationTv.text = item.duration.getOrDefault(mContext)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCatalogueDetail(args.mediaId)
    }
}
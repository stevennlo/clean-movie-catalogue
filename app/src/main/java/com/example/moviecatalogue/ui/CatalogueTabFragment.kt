package com.example.moviecatalogue.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.domain.Resource
import com.example.core.domain.model.MediaFormat
import com.example.core.util.DataState
import com.example.core.util.EXTRA_MEDIA_FORMAT
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CatalogueAdapter
import com.example.moviecatalogue.databinding.FragmentCatalogueTabBinding
import com.example.moviecatalogue.viewmodel.CatalogueViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@FlowPreview
class CatalogueTabFragment :
    BaseFragment<FragmentCatalogueTabBinding>(FragmentCatalogueTabBinding::inflate) {
    private val viewModel: CatalogueViewModel by viewModel()
    private lateinit var format: MediaFormat

    override fun runOnCreateView() {
        super.runOnCreateView()
        binding.apply {
            val catalogueAdapter = CatalogueAdapter(this@CatalogueTabFragment::navigateToDetail)
            catalogueTabRefreshSrl.setOnRefreshListener {
                viewModel.refreshSearch()
            }
            catalogueTabListRv.apply {
                adapter = catalogueAdapter
                layoutManager = GridLayoutManager(mContext, 2)
            }
            viewModel.medias.observe(viewLifecycleOwner, {
                if (it is Resource.Error) showResult(
                    DataState.ERROR,
                    getString(R.string.unknown_error_message)
                )
                else if (it is Resource.Success) {
                    if (it.data?.isEmpty() == true) showResult(
                        DataState.NOT_FOUND,
                        getString(R.string.nothing_here)
                    ) else {
                        catalogueAdapter.submitList(it.data)
                        showResult(DataState.EXISTS)
                    }
                }
                binding.catalogueTabRefreshSrl.isRefreshing = false
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        format = arguments?.getSerializable(EXTRA_MEDIA_FORMAT) as MediaFormat
        viewModel.setSearchFormat(format)
        viewModel.getMedias(format)
    }

    override fun onResume() {
        super.onResume()
        binding.catalogueTabRefreshSrl.isEnabled = true
    }

    override fun onPause() {
        binding.catalogueTabRefreshSrl.isEnabled = false
        super.onPause()
    }

    override fun onDestroyView() {
        binding.catalogueTabListRv.adapter = null
        super.onDestroyView()
    }

    private fun navigateToDetail(mediaId: Int) {
        val destination =
            CatalogueFragmentDirections.actionCatalogueFragmentToCatalogueDetailFragment(mediaId)
        findNavController().navigate(destination)
    }

    fun searchMedias(keyword: String) {
        viewModel.queryChannel.trySend(keyword)
    }

    fun clearSearchMedias() {
        viewModel.queryChannel.trySend("")
    }

    fun getLastKeyword(): String = viewModel.keyword

    override fun getRootViewGroup(): ViewGroup = binding.catalogueTabRootCl
}
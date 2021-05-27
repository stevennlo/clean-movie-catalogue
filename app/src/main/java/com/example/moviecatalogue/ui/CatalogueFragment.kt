package com.example.moviecatalogue.ui

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.core.util.DataState
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.CataloguePagerAdapter
import com.example.moviecatalogue.databinding.FragmentCatalogueBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class CatalogueFragment :
    BaseFragment<FragmentCatalogueBinding>(FragmentCatalogueBinding::inflate) {
    private lateinit var searchView: SearchView
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun runOnCreateView() {
        super.runOnCreateView()
        showResult(DataState.EXISTS)
        binding.apply {
            val cataloguePagerAdapter =
                CataloguePagerAdapter(
                    childFragmentManager,
                    viewLifecycleOwner.lifecycle
                )
            catalogueViewVp2.isUserInputEnabled = false
            catalogueViewVp2.adapter = cataloguePagerAdapter
            (catalogueViewVp2.getChildAt(0) as ViewGroup).clipChildren = false
            tabLayoutMediator =
                TabLayoutMediator(catalogueTabsTl, catalogueViewVp2) { tab, position ->
                    when (position) {
                        0 -> tab.text = getString(R.string.tv_show_tab)
                        1 -> tab.text = getString(R.string.movie_tab)
                    }
                }
            tabLayoutMediator?.attach()
        }
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        searchView.setOnQueryTextListener(null)
        binding.catalogueViewVp2.adapter = null
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        super.onDestroyView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.catalogue_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val searchMenu = menu.findItem(R.id.catalogue_search_item)
        searchView = searchMenu.actionView as SearchView
        val searchManager = mContext.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val closeButton = searchView.findViewById<ImageView>(R.id.search_close_btn)
        closeButton.setOnClickListener {
            setSearchValue(searchView, "", true)
            searchMenu.collapseActionView()
        }
        searchView.apply {
            maxWidth = Integer.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo((mContext as AppCompatActivity).componentName))
            queryHint = resources.getString(R.string.search_hint)
            getCurrentChildFragment()?.let {
                if (it.getLastKeyword().isNotEmpty()) {
                    searchMenu.expandActionView()
                }
                setSearchValue(this, it.getLastKeyword(), false)
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(keyword: String): Boolean {
                    getCurrentChildFragment()?.searchMedias(keyword)
                    return false
                }

                override fun onQueryTextChange(keyword: String): Boolean {
                    if (keyword.isEmpty()) {
                        getCurrentChildFragment()?.clearSearchMedias()
                    }
                    return false
                }
            })
        }
    }

    private fun setSearchValue(searchView: SearchView, keyword: String, submit: Boolean) {
        searchView.apply {
            findViewById<EditText>(R.id.search_src_text).setText(keyword)
            setQuery(keyword, submit)
        }
    }

    internal fun getCurrentChildFragment(): CatalogueTabFragment? {
        val childFragment =
            childFragmentManager.findFragmentByTag("f${binding.catalogueViewVp2.currentItem}")
        return childFragment as CatalogueTabFragment?
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.catalogue_favorite_menu_item -> findNavController().navigate(R.id.action_catalogueFragment_to_favoriteFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}
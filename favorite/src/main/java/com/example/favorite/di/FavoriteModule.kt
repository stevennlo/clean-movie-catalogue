package com.example.favorite.di

import com.example.favorite.viewmodel.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
}
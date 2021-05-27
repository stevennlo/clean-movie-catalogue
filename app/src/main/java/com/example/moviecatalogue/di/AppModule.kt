package com.example.moviecatalogue.di

import com.example.core.domain.usecase.MediaInteractor
import com.example.core.domain.usecase.MediaUseCase
import com.example.moviecatalogue.viewmodel.CatalogueDetailViewModel
import com.example.moviecatalogue.viewmodel.CatalogueViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MediaUseCase> { MediaInteractor(get()) }
}

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { CatalogueViewModel(get()) }
    viewModel { CatalogueDetailViewModel(get()) }
}
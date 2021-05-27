package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.domain.model.Media
import com.example.core.domain.usecase.MediaUseCase
import com.example.core.util.getOrAwaitValue
import com.example.moviecatalogue.util.DummyData.getMedia
import com.example.moviecatalogue.util.DummyData.getMediaId
import io.mockk.*
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatalogueDetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val mediaUseCase = mockk<MediaUseCase>()
    private val viewModel = CatalogueDetailViewModel(mediaUseCase)
    private val media = getMedia()
    private val mediaId = getMediaId()

    @Before
    fun setUp() {
        every { mediaUseCase.getMedia(mediaId) } answers { flow { emit(media) } }
        every { mediaUseCase.setMediaFavorite(media, media.isFavorite xor true) } just Runs
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetCatalogueDetail() {
        val observer = spyk<Observer<Media>>()
        viewModel.media.observeForever(observer)
        viewModel.getCatalogueDetail(mediaId)
        verify {
            observer.onChanged(media)
        }
    }

    @Test
    fun testToggleMediaFavorite() {
        viewModel.getCatalogueDetail(mediaId)
        viewModel.media.getOrAwaitValue()
        viewModel.toggleFavorite()
        verify {
            mediaUseCase.setMediaFavorite(media, media.isFavorite xor true)
        }
    }
}
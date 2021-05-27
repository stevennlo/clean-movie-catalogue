package com.example.favorite.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.domain.usecase.MediaUseCase
import com.example.favorite.util.DummyData.getMediaFormat
import com.example.favorite.util.DummyData.getMedias
import io.mockk.*
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val mediaUseCase = mockk<MediaUseCase>()
    private val viewModel = FavoriteViewModel(mediaUseCase)
    private val format = getMediaFormat()
    private val medias = getMedias()

    @Before
    fun setUp() {
        every { mediaUseCase.getFavoriteMedias(format) } answers { flow { emit(medias) } }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetMediasFavorite() {
        val observer = spyk<Observer<in List<com.example.core.domain.model.Media>>>()
        viewModel.medias.observeForever(observer)
        viewModel.getMediasFavorite(getMediaFormat())
        verify {
            observer.onChanged(medias)
        }
    }
}
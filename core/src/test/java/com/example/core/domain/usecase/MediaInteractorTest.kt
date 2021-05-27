package com.example.core.domain.usecase

import com.example.core.domain.Resource
import com.example.core.domain.repository.MediaRepository
import com.example.core.util.DummyData.getKeyword
import com.example.core.util.DummyData.getMedia
import com.example.core.util.DummyData.getMediaFormat
import com.example.core.util.DummyData.getMediaId
import com.example.core.util.DummyData.getMedias
import io.mockk.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MediaInteractorTest {
    private val mediaRepository = mockk<MediaRepository>()
    private val mediaInteractor = MediaInteractor(mediaRepository)
    private val format = getMediaFormat()
    private val mediasResponse = Resource.Success(getMedias())
    private val keyword = getKeyword()
    private val medias = getMedias()
    private val mediaId = getMediaId()
    private val media = getMedia()
    private val isFavorite = false

    @Before
    fun setUp() {
        every { mediaRepository.getMedias(format) } answers { flow { emit(mediasResponse) } }
        every {
            mediaRepository.searchMedias(
                format,
                keyword
            )
        } answers { flow { emit(mediasResponse) } }
        every { mediaRepository.getFavoriteMedias(format) } answers { flow { emit(medias) } }
        every { mediaRepository.getMedia(mediaId) } answers { flow { emit(media) } }
        every { mediaRepository.setMediaFavorite(media, isFavorite) } just Runs
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetMedias() {
        runBlocking {
            mediaInteractor.getMedias(format).collectLatest {
                assertEquals(mediasResponse, it)
            }
        }
    }

    @Test
    fun testSearchMedias() {
        runBlocking {
            mediaInteractor.searchMedias(format, keyword).collectLatest {
                assertEquals(mediasResponse, it)
            }
        }
    }

    @Test
    fun testGetFavoriteMedias() {
        runBlocking {
            mediaInteractor.getFavoriteMedias(format).collectLatest {
                assertEquals(medias, it)
            }
        }
    }

    @Test
    fun testGetMedia() {
        runBlocking {
            mediaInteractor.getMedia(mediaId).collectLatest {
                assertEquals(media, it)
            }
        }
    }

    @Test
    fun testSetMediaFavorite() {
        mediaInteractor.setMediaFavorite(media, isFavorite)
        verify {
            mediaRepository.setMediaFavorite(media, isFavorite)
        }
    }
}
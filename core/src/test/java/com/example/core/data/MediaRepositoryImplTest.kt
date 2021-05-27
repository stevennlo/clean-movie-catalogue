package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.domain.Resource
import com.example.core.util.DummyData.getKeyword
import com.example.core.util.DummyData.getMedia
import com.example.core.util.DummyData.getMediaEntities
import com.example.core.util.DummyData.getMediaEntity
import com.example.core.util.DummyData.getMediaFormat
import com.example.core.util.DummyData.getMediaId
import com.example.core.util.DummyData.getMedias
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MediaRepositoryImplTest {
    private val remoteDataSource = mockk<RemoteDataSource>()
    private val localDataSource = mockk<LocalDataSource>()
    private val mediaRepository = MediaRepositoryImpl(remoteDataSource, localDataSource)
    private val format = getMediaFormat()
    private val mediaEntities = getMediaEntities()
    private val medias = getMedias()
    private val isFavorite = true
    private val mediaEntity = getMediaEntity()
    private val media = getMedia()
    private val mediaId = getMediaId()
    private val keyword = getKeyword()

    @Before
    fun setUp() {
        every { localDataSource.getMedias(format) } answers { flow { emit(mediaEntities) } }
        every { localDataSource.getMediasFavorite(format, true) } answers {
            flow {
                emit(
                    mediaEntities
                )
            }
        }
        every { localDataSource.setMediaFavorite(mediaEntity, isFavorite) } just Runs
        every { localDataSource.getMediaById(mediaId) } answers { flow { emit(mediaEntity) } }
        every {
            localDataSource.searchMedias(
                format,
                keyword
            )
        } answers { flow { emit(mediaEntities) } }
        every { localDataSource.getMediaById(mediaId) } answers { flow { emit(mediaEntity) } }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testGetMedias() {
        runBlocking {
            val result = mediaRepository.getMedias(format).last()
            assertTrue(result is Resource.Success)
            assertEquals(medias, result.data)
        }
    }

    @Test
    fun testGetFavoriteMedias() {
        runBlocking {
            val result = mediaRepository.getFavoriteMedias(format).last()
            assertEquals(medias, result)
        }
    }

    @Test
    fun testSearchMedias() {
        runBlocking {
            val result = mediaRepository.searchMedias(format, keyword).last()
            assertTrue(result is Resource.Success)
            assertEquals(medias, result.data)
        }
    }

    @Test
    fun testGetMedia() {
        runBlocking {
            val result = mediaRepository.getMedia(mediaId).last()
            assertEquals(media, result)
        }
    }

    @Test
    fun testSetMediaFavorite() {
        mediaRepository.setMediaFavorite(media, isFavorite)
        verify {
            localDataSource.setMediaFavorite(mediaEntity, isFavorite)
        }
    }
}
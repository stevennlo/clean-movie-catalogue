package com.example.core.data.source.local

import com.example.core.data.source.local.room.MediaDao
import com.example.core.util.DummyData.getKeyword
import com.example.core.util.DummyData.getMediaEntities
import com.example.core.util.DummyData.getMediaEntity
import com.example.core.util.DummyData.getMediaFormat
import com.example.core.util.DummyData.getMediaId
import io.mockk.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {
    private val mediaDao = mockk<MediaDao>()
    private val localDataSource = LocalDataSource(mediaDao)
    private val mediaEntities = getMediaEntities()
    private val keyword = getKeyword()
    private val mediaEntity = getMediaEntity()
    private val format = getMediaFormat()
    private val mediaId = getMediaId()

    @Before
    fun setUp() {
        coEvery { mediaDao.insert(mediaEntities) } just Runs
        every {
            mediaDao.getMediasByFormatIsFavoriteAndKeyword(
                format,
                true
            )
        } answers { flow { emit(mediaEntities) } }
        every { mediaDao.getMediasByFormatAndKeyword(format) } answers { flow { emit(mediaEntities) } }
        every { mediaDao.getMediasByFormatAndKeyword(format, keyword) } answers {
            flow {
                emit(
                    mediaEntities
                )
            }
        }
        every { mediaDao.update(mediaEntity) } just Runs
        every { mediaDao.getById(mediaId) } answers { flow { emit(mediaEntity) } }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testInsertMedias() {
        runBlocking {
            localDataSource.insertMedias(mediaEntities)
            coVerify {
                mediaDao.insert(mediaEntities)
            }
        }
    }

    @Test
    fun testGetMediasFavorite() {
        runBlocking {
            localDataSource.getMediasFavorite(format, true).collectLatest {
                assertEquals(mediaEntities, it)
            }
        }
    }

    @Test
    fun testGetMedias() {
        runBlocking {
            localDataSource.getMedias(format).collectLatest {
                assertEquals(mediaEntities, it)
            }
        }
    }

    @Test
    fun testSearchMedias() {
        runBlocking {
            localDataSource.searchMedias(format, keyword).collectLatest {
                assertEquals(mediaEntities, it)
            }
        }
    }

    @Test
    fun testSetMediaFavorite() {
        localDataSource.setMediaFavorite(mediaEntity, false)
        verify {
            mediaDao.update(mediaEntity)
        }
    }

    @Test
    fun testGetMediaById() {
        runBlocking {
            localDataSource.getMediaById(mediaId).collectLatest {
                assertEquals(mediaEntity, it)
            }
        }
    }
}
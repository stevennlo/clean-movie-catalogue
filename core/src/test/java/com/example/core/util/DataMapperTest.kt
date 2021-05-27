package com.example.core.util

import com.example.core.util.DataMapper.mapToMedia
import com.example.core.util.DataMapper.mapToMediaEntities
import com.example.core.util.DataMapper.mapToMediaEntity
import com.example.core.util.DataMapper.mapToMedias
import com.example.core.util.DummyData.getMedia
import com.example.core.util.DummyData.getMediaEntities
import com.example.core.util.DummyData.getMediaEntity
import com.example.core.util.DummyData.getMedias
import com.example.core.util.DummyData.getMediumList
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {
    private val mediumList = getMediumList()
    private val mediaEntities = getMediaEntities(false)
    private val medias = getMedias(false)
    private val media = getMedia()
    private val mediaEntity = getMediaEntity()

    @Test
    fun testMediumListMapToMediaEntities() {
        assertEquals(mediaEntities, mediumList.mapToMediaEntities())
    }

    @Test
    fun testMediaEntitiesMapToMedias() {
        assertEquals(medias, mediaEntities.mapToMedias())
    }

    @Test
    fun testMediaMapToMediaEntity() {
        assertEquals(mediaEntity, media.mapToMediaEntity())
    }

    @Test
    fun testMediaEntityMapToMedia() {
        assertEquals(media, mediaEntity.mapToMedia())
    }
}
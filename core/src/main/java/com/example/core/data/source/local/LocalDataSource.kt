package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MediaEntity
import com.example.core.data.source.local.room.MediaDao
import com.example.core.domain.model.MediaFormat
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mediaDao: MediaDao) {
    suspend fun insertMedias(medias: List<MediaEntity>) {
        mediaDao.insert(medias)
    }

    fun getMediasFavorite(format: MediaFormat, isFavorite: Boolean): Flow<List<MediaEntity>> =
        mediaDao.getMediasByFormatIsFavoriteAndKeyword(format, isFavorite)

    fun getMedias(format: MediaFormat): Flow<List<MediaEntity>> =
        mediaDao.getMediasByFormatAndKeyword(format)

    fun searchMedias(format: MediaFormat, keyword: String) =
        mediaDao.getMediasByFormatAndKeyword(format, keyword)

    fun setMediaFavorite(media: MediaEntity, isFavorite: Boolean) {
        media.isFavorite = isFavorite
        mediaDao.update(media)
    }

    fun getMediaById(id: Int) = mediaDao.getById(id)
}
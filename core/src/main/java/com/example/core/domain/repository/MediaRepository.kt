package com.example.core.domain.repository

import com.example.core.domain.Resource
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import kotlinx.coroutines.flow.Flow

interface MediaRepository {
    fun getMedias(format: MediaFormat): Flow<Resource<List<Media>>>
    fun getFavoriteMedias(format: MediaFormat): Flow<List<Media>>
    fun searchMedias(format: MediaFormat, keyword: String): Flow<Resource<List<Media>>>
    fun getMedia(mediaId: Int): Flow<Media>
    fun setMediaFavorite(media: Media, isFavorite: Boolean)
}
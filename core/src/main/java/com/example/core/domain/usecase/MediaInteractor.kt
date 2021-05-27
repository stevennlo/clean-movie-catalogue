package com.example.core.domain.usecase

import com.example.core.domain.Resource
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import com.example.core.domain.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

class MediaInteractor(private val mediaRepository: MediaRepository) : MediaUseCase {
    override fun getMedias(format: MediaFormat): Flow<Resource<List<Media>>> =
        mediaRepository.getMedias(format)

    override fun searchMedias(format: MediaFormat, keyword: String): Flow<Resource<List<Media>>> =
        mediaRepository.searchMedias(format, keyword)

    override fun getFavoriteMedias(format: MediaFormat): Flow<List<Media>> =
        mediaRepository.getFavoriteMedias(format)

    override fun getMedia(mediaId: Int): Flow<Media> =
        mediaRepository.getMedia(mediaId)

    override fun setMediaFavorite(media: Media, isFavorite: Boolean) =
        mediaRepository.setMediaFavorite(media, isFavorite)
}
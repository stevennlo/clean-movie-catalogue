package com.example.core.util

import com.example.core.data.source.local.entity.MediaEntity
import com.example.core.data.source.remote.model.MediasQuery
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat

object DataMapper {
    fun List<MediasQuery.Medium>.mapToMediaEntities(): List<MediaEntity> =
        this.map { medium ->
            MediaEntity(
                medium.id,
                medium.title?.romaji as String,
                medium.coverImage?.large ?: "",
                MediaFormat.valueOf(medium.format?.name as String),
                medium.episodes ?: 0,
                medium.description ?: "-",
                medium.bannerImage ?: "",
                medium.duration ?: 0,
                medium.averageScore ?: 0,
                medium.favourites ?: 0,
                false
            )
        }

    fun List<MediaEntity>.mapToMedias(): List<Media> =
        this.map { mediaEntity ->
            mediaEntity.mapToMedia()
        }

    fun Media.mapToMediaEntity(): MediaEntity = MediaEntity(
        id,
        title,
        imageUrl,
        format,
        episodes,
        description,
        bannerImageUrl,
        duration,
        averageScore,
        favorites,
        isFavorite
    )

    fun MediaEntity.mapToMedia() = Media(
        id,
        title,
        imageUrl,
        format,
        episodes,
        description,
        bannerImageUrl,
        duration,
        averageScore,
        favorites,
        isFavorite
    )
}
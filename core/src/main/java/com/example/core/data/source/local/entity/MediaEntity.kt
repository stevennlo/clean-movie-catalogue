package com.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.domain.model.MediaFormat
import com.example.core.util.MEDIAS_TABLE_NAME

@Entity(tableName = MEDIAS_TABLE_NAME)
data class MediaEntity(
    @PrimaryKey
    val id: Int,
    var title: String,
    var imageUrl: String,
    var format: MediaFormat,
    var episodes: Int,
    var description: String,
    var bannerImageUrl: String,
    var duration: Int,
    var averageScore: Int,
    var favorites: Int,
    var isFavorite: Boolean = false
)
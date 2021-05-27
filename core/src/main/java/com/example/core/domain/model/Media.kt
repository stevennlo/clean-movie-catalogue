package com.example.core.domain.model

data class Media(
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
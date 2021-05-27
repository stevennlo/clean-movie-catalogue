package com.example.moviecatalogue.util

import com.example.moviecatalogue.R

object CatalogueUtil {
    fun getEmoResourceId(score: Int?): Int {
        val emojiResources = listOf(R.drawable.ic_sad, R.drawable.ic_neutral, R.drawable.ic_smile)
        return when (score) {
            null -> R.drawable.ic_neutral
            else -> {
                val finalScore = if (score > 100) 100 else score
                emojiResources[finalScore / 34]
            }
        }
    }
}
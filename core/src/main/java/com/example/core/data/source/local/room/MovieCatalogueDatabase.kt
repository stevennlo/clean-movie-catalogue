package com.example.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MediaEntity

@Database(entities = [MediaEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {
    abstract fun mediasFavoriteDao(): MediaDao
}
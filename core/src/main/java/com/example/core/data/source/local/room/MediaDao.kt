package com.example.core.data.source.local.room

import androidx.room.*
import com.example.core.data.source.local.entity.MediaEntity
import com.example.core.domain.model.MediaFormat
import com.example.core.util.MEDIAS_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {
    @Query("SELECT * FROM $MEDIAS_TABLE_NAME WHERE format = :format and title LIKE '%' || :keyword || '%'")
    fun getMediasByFormatAndKeyword(
        format: MediaFormat,
        keyword: String = ""
    ): Flow<List<MediaEntity>>

    @Query("SELECT * FROM $MEDIAS_TABLE_NAME WHERE format = :format and isFavorite = :isFavorite and title LIKE '%' || :keyword || '%'")
    fun getMediasByFormatIsFavoriteAndKeyword(
        format: MediaFormat,
        isFavorite: Boolean,
        keyword: String = ""
    ): Flow<List<MediaEntity>>

    @Query("SELECT * FROM $MEDIAS_TABLE_NAME WHERE id = :id")
    fun getById(id: Int): Flow<MediaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mediasFavorite: List<MediaEntity>)

    @Query("DELETE FROM $MEDIAS_TABLE_NAME WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun update(mediaEntity: MediaEntity)
}
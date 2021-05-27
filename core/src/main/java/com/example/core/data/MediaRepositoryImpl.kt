package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.model.MediasQuery
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.Resource
import com.example.core.domain.model.Media
import com.example.core.domain.model.MediaFormat
import com.example.core.domain.repository.MediaRepository
import com.example.core.util.DataMapper.mapToMedia
import com.example.core.util.DataMapper.mapToMediaEntities
import com.example.core.util.DataMapper.mapToMediaEntity
import com.example.core.util.DataMapper.mapToMedias
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MediaRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :
    MediaRepository {
    override fun getMedias(
        format: MediaFormat
    ): Flow<Resource<List<Media>>> =
        object : NetworkBoundResource<List<Media>, List<MediasQuery.Medium>>() {
            override fun loadFromDB(): Flow<List<Media>> {
                return localDataSource.getMedias(format).map {
                    it.mapToMedias()
                }
            }

            override fun shouldFetch(data: List<Media>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MediasQuery.Medium>>> {
                return remoteDataSource.getMedias(format)
            }

            override suspend fun saveCallResult(data: List<MediasQuery.Medium>) {
                val movieList = data.mapToMediaEntities()
                localDataSource.insertMedias(movieList)
            }
        }.asFlow()

    override fun getFavoriteMedias(format: MediaFormat): Flow<List<Media>> =
        localDataSource.getMediasFavorite(format, true).map {
            it.mapToMedias()
        }

    override fun searchMedias(format: MediaFormat, keyword: String): Flow<Resource<List<Media>>> =
        object : NetworkBoundResource<List<Media>, List<MediasQuery.Medium>>() {
            override fun loadFromDB(): Flow<List<Media>> {
                return localDataSource.searchMedias(format, keyword).map { it.mapToMedias() }
            }

            override fun shouldFetch(data: List<Media>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MediasQuery.Medium>>> {
                return remoteDataSource.getMedias(format)
            }

            override suspend fun saveCallResult(data: List<MediasQuery.Medium>) {
                val movieList = data.mapToMediaEntities()
                localDataSource.insertMedias(movieList)
            }

        }.asFlow()

    override fun getMedia(mediaId: Int): Flow<Media> =
        localDataSource.getMediaById(mediaId).map { it.mapToMedia() }

    override fun setMediaFavorite(media: Media, isFavorite: Boolean) {
        localDataSource.setMediaFavorite(media.mapToMediaEntity(), isFavorite)
    }
}
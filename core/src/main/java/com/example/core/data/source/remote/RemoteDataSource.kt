package com.example.core.data.source.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.example.core.data.source.remote.model.MediasQuery
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.domain.model.MediaFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import com.example.core.data.source.remote.model.type.MediaFormat as RequestMediaFormat

class RemoteDataSource(private val apolloClient: ApolloClient) {
    fun getMedias(format: MediaFormat): Flow<ApiResponse<List<MediasQuery.Medium>>> {
        return flow {
            try {
                val response =
                    apolloClient.query(MediasQuery(RequestMediaFormat.valueOf(format.name))).await()
                if (response.data?.page?.media?.isNotEmpty() == true) {
                    response.data?.page?.media?.let {
                        emit(ApiResponse.Success(it.filterNotNull()))
                    }
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: ApolloException) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
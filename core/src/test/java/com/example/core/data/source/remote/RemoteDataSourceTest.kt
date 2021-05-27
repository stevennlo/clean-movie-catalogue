package com.example.core.data.source.remote

import com.apollographql.apollo.ApolloClient
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.util.DummyData.BASE_TEST_PORT
import com.example.core.util.DummyData.getMediaFormat
import com.example.core.util.DummyData.getMediasBody
import com.example.core.util.DummyData.getMediumList
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection.HTTP_OK

class RemoteDataSourceTest {
    private val mockWebServer = MockWebServer()
    private lateinit var remoteDataSource: RemoteDataSource
    private val mediasBody = getMediasBody()
    private val format = getMediaFormat()
    private val mediumList = getMediumList()

    @Before
    fun setUp() {
        mockWebServer.start(BASE_TEST_PORT)
        val apolloClient = ApolloClient.builder()
            .serverUrl(mockWebServer.url("/"))
            .build()
        remoteDataSource = RemoteDataSource(apolloClient)
    }

    @After
    fun tearDown() {
        unmockkAll()
        mockWebServer.shutdown()
    }

    @Test
    fun testGetMedias() {
        mockWebServer.enqueue(MockResponse().setResponseCode(HTTP_OK).setBody(mediasBody))
        runBlocking {
            val apiResponse = remoteDataSource.getMedias(format).first()
            assertTrue(apiResponse is ApiResponse.Success)
            assertEquals(mediumList, (apiResponse as ApiResponse.Success).data)
        }
    }
}
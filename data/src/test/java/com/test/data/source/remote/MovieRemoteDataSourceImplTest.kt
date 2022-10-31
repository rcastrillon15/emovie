package com.test.data.source.remote

import com.test.common.Either
import com.test.data.apiservice.IMovieService
import com.test.data.source.enqueueResponse
import com.test.data.source.remote.MovieRemoteDummy.detailResponseDummy
import com.test.data.source.remote.MovieRemoteDummy.topRatedResponseDummy
import com.test.data.source.remote.MovieRemoteDummy.trailerResponseDummy
import com.test.data.source.remote.MovieRemoteDummy.upcomingResponseDummy
import com.test.data.source.toEiterRight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Test class of [MovieRemoteDataSourceImpl]
 */
@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IMovieService::class.java)

    private val dataSource = MovieRemoteDataSourceImpl(api)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Given upcoming movies When getComing is called Then the service return UpcomingResponse with code 200`() {
         mockWebServer.enqueueResponse("mock_upcoming_movie.json", 200)
        runBlocking {
            //Given
            val page = 1
            val result = upcomingResponseDummy().toEiterRight()

            //When
            val response = dataSource.getUpcoming(page)

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }

    @Test
    fun `Given movies When getDetail is called Then the service return DetailResponse with code 200`() {
        mockWebServer.enqueueResponse("mock_detail_movie.json", 200)
        runBlocking {
            //Given
            val movieId = 634649
            val result = detailResponseDummy().toEiterRight()

            //When
            val response = dataSource.getDetail(movieId)

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }

    @Test
    fun `Given topRated movies When getTopRated is called Then the service return TopRatedResponse with code 200`() {
        mockWebServer.enqueueResponse("mock_toprated_movie.json", 200)
        runBlocking {
            //Given
            val page = 1
            val result = topRatedResponseDummy().toEiterRight()

            //When
            val response = dataSource.getTopRated(page)

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }

    @Test
    fun `Given movies When getTrailer is called Then the service return TrailerResponse with code 200`() {
        mockWebServer.enqueueResponse("mock_trailer_movie.json", 200)
        runBlocking {
            //Given
            val movieId = 634649
            val result = trailerResponseDummy().toEiterRight()

            //When
            val response = dataSource.getTrailer(movieId)

            //Verify
            assert(response is Either.Right)
            assertEquals(result, response)
        }
    }
}

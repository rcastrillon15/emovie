package com.test.domain.repositories

import com.test.common.Constants
import com.test.common.Either
import com.test.data.source.local.MovieLocalDataSourceImpl
import com.test.data.source.remote.MovieRemoteDataSourceImpl
import com.test.domain.detailModelDummy
import com.test.domain.detailResponseDummy
import com.test.domain.getComingModel
import com.test.domain.getTopRatedModelDummy
import com.test.domain.getTrailerResponseDummy
import com.test.domain.getUpcomingEntityDummy
import com.test.domain.getUpcomingResponse
import com.test.domain.topRatedEntityDummy
import com.test.domain.topRatedResponseDummy
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class of [MovieRepositoryImpl]
 */
@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    private lateinit var repository: MovieRepositoryImpl

    @MockK
    private lateinit var remoteDataSource: MovieRemoteDataSourceImpl

    @MockK
    private lateinit var localDataSource: MovieLocalDataSourceImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun `Given getUpcoming right value When data source is executed Verify expected response`() =
        runBlocking {
            //Given
            val page = 1
            val upcomingResponse = getUpcomingResponse()
            val upcomingEntity = getUpcomingEntityDummy()
            val upcomingModel = getComingModel()

            //When
            coEvery { remoteDataSource.getUpcoming(page) } returns Either.Right(upcomingResponse)
            coEvery { localDataSource.getUpcoming() } returns Either.Right(upcomingEntity)
            val result = repository.getUpcoming(page)

            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, upcomingModel)
            Assert.assertTrue(result.r.isNotEmpty())
            Assert.assertTrue(result.r.single().id == 123456)
            coVerify(exactly = 1) {
                remoteDataSource.getUpcoming(page)
            }
        }

    @Test
    fun `Given getTopRated right value When data source is executed Verify expected response`() =
        runBlocking {
            //Given
            val page = 1
            val topRatedResponse = topRatedResponseDummy()
            val topRatedEntity = topRatedEntityDummy()
            val topRatedModel = getTopRatedModelDummy()

            //When
            coEvery { remoteDataSource.getTopRated(page) } returns Either.Right(topRatedResponse)
            coEvery { localDataSource.getTopRated() } returns Either.Right(topRatedEntity)
            val result = repository.getTopRated(page)

            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, topRatedModel)
            Assert.assertTrue(result.r.isNotEmpty())
            Assert.assertTrue(result.r.single().id == 667556)
            coVerify(exactly = 1) {
                remoteDataSource.getTopRated(page)
            }
        }

    @Test
    fun `Given getDetail right value When data source is executed Verify expected response`() =
        runBlocking {
            //Given
            val movieId = 454353
            val detailResponse = detailResponseDummy()
            val detailModeModel = detailModelDummy()

            //When
            coEvery { remoteDataSource.getDetail(movieId) } returns Either.Right(detailResponse)
            val result = repository.getDetail(movieId)

            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r, detailModeModel)
            Assert.assertTrue(result.r.id == movieId)
            coVerify(exactly = 1) {
                remoteDataSource.getDetail(movieId)
            }
        }

    @Test
    fun `Given getTrailer right value When data source is executed Verify expected response`() =
        runBlocking {
            //Given
            val movieId = 454353
            val trailerResponse = getTrailerResponseDummy()
            val key = "${Constants.TRAILER_URL}Uty1B1GuO7E"

            //When
            coEvery { remoteDataSource.getTrailer(movieId) } returns Either.Right(trailerResponse)
            val result = repository.getTrailer(movieId)

            //Verify
            Assert.assertTrue(result is Either.Right)
            Assert.assertEquals((result as Either.Right).r?.single(), key)
            coVerify(exactly = 1) {
                remoteDataSource.getTrailer(movieId)
            }
        }
}

package com.test.data.source.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.test.data.db.AppDatabase
import com.test.data.db.IMovieLocal
import com.test.data.source.local.MovieDummy.topRatedDummy
import com.test.data.source.local.MovieDummy.upComingDummy
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Test class of [MovieLocalDataSourceImpl]
 */
@HiltAndroidTest
@SmallTest
class MovieLocalDataSourceImplTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var database: AppDatabase
    private lateinit var movieDao: IMovieLocal

    @Before
    fun setUp() {
        hiltRule.inject()

        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        movieDao = database.iMovieLocal()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun given_upcoming_movies_When_transactionUpcoming_is_execute_Then_database_return_list_UpcomingEntity() =
        runBlocking {
            //Given
            val upcomingMovies = upComingDummy()

            //When
            movieDao.transactionUpcoming(upcomingMovies)
            val result = movieDao.selectUpcoming()

            //Verify
            assert(result.isNotEmpty())
            assertEquals(result.single().id, upcomingMovies.single().id)
            assertEquals(result.single().overview, upcomingMovies.single().overview)
        }

    @Test
    fun given_upcoming_movies_When_selectUpcoming_is_execute_Then_database_return_list_UpcomingEntity() =
        runBlocking {
            //Given
            val upcomingMovies = upComingDummy()

            //When
            movieDao.insertUpcoming(upcomingMovies)
            val result = movieDao.selectUpcoming()

            //Verify
            assert(result.isNotEmpty())
            assertEquals(result.single().id, upcomingMovies.single().id)
            assertEquals(result.single().release_date, upcomingMovies.single().release_date)
        }

    @Test
    fun given_empty_movies_When_deleteUpcoming_is_execute_Then_database_return_list_with_size_zero() =
        runBlocking {
            //Given
            val upcomingMovies = upComingDummy()

            //When
            movieDao.insertUpcoming(upcomingMovies)
            movieDao.deleteUpcoming()
            val allMovies = movieDao.selectUpcoming()
            assertEquals(allMovies.size, 0)
        }

    @Test
    fun given_topRated_movies_When_transactionTopRated_is_execute_Then_database_return_list_TopRatedEntity() =
        runBlocking {
            //Given
            val topRatedMovies = topRatedDummy()

            //When
            movieDao.transactionTopRated(topRatedMovies)
            val result = movieDao.selectTopRated()

            //Verify
            assert(result.isNotEmpty())
            assertEquals(result.single().backdrop_path, topRatedMovies.single().backdrop_path)
        }

    @Test
    fun given_topRated_movies_When_selectTopRated_is_execute_Then_database_return_list_TopRatedEntity() =
        runBlocking {
            //Given
            val topRatedMovies = topRatedDummy()

            //When
            movieDao.insertTopRated(topRatedMovies)
            val result = movieDao.selectTopRated()

            //Verify
            assert(result.isNotEmpty())
            assertEquals(result.single().id, topRatedMovies.single().id)
            assertEquals(result.single().original_title, topRatedMovies.single().original_title)
        }

    @Test
    fun given_empty_movies_When_deleteTopRated_is_execute_Then_database_return_list_with_size_zero() =
        runBlocking {
            //Given
            val topRatedMovies = topRatedDummy()

            //When
            movieDao.insertTopRated(topRatedMovies)
            movieDao.deleteTopRated()
            val result = movieDao.selectTopRated()

            //Verify
            assertEquals(result.size, 0)
        }
}

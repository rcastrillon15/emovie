package com.test.domain.repositories

import com.test.common.Constants.TRAILER_URL
import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.common.framework.NetworkMonitor
import com.test.data.source.local.MovieLocalDataSourceImpl
import com.test.data.source.remote.MovieRemoteDataSourceImpl
import com.test.domain.mappers.toDetailModel
import com.test.domain.mappers.toTopRatedEntity
import com.test.domain.mappers.toTopRatedModel
import com.test.domain.mappers.toUpcomingEntity
import com.test.domain.mappers.toUpcomingModel

import com.test.domain.models.DetailModel
import com.test.domain.models.TopRatedModel
import com.test.domain.models.UpcomingModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSourceImpl,
    private val localDataSource: MovieLocalDataSourceImpl
) : MovieRepository {
    override suspend fun getUpcoming(page: Int): Either<DomainErrorFactory, List<UpcomingModel>> {

        if (NetworkMonitor().isConnected()) {
            return when (val response = remoteDataSource.getUpcoming(page)) {
                is Either.Right -> {
                    localDataSource.insertUpcoming(response.r.results.map { it.toUpcomingEntity() })
                    Either.Right(response.r.results.map { it.toUpcomingModel() })
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            return when (val response = localDataSource.getUpcoming()) {
                is Either.Right -> {
                    Either.Right(response.r.map { it.toUpcomingModel() })
                }

                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        }
    }

    override suspend fun getTopRated(page: Int): Either<DomainErrorFactory, List<TopRatedModel>> {
        if (NetworkMonitor().isConnected()) {
            return when (val response = remoteDataSource.getTopRated(page)) {
                is Either.Right -> {
                    localDataSource.insertTopRated(response.r.results.map { it.toTopRatedEntity() })
                    Either.Right(response.r.results.map { it.toTopRatedModel() })
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            return when (val response = localDataSource.getTopRated()) {
                is Either.Right -> {
                    Either.Right(response.r.map { it.toTopRatedModel() })
                }

                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        }
    }


    override suspend fun getDetail(movieId: Int): Either<DomainErrorFactory, DetailModel> {
        return if (NetworkMonitor().isConnected()) {
            when (val response = remoteDataSource.getDetail(movieId)) {
                is Either.Right -> {
                    Either.Right(response.r.toDetailModel())
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            Either.Left(DomainErrorFactory(errorCode = -2))
        }
    }

    override suspend fun getTrailer(movieId: Int): Either<DomainErrorFactory, List<String>?> {
        return if (NetworkMonitor().isConnected()) {
            when (val response = remoteDataSource.getTrailer(movieId)) {
                is Either.Right -> {
                    Either.Right(checkNotNull(response.r.results?.map { "$TRAILER_URL${it.key}" }))
                }
                is Either.Left -> {
                    Either.Left(response.l)
                }
            }
        } else {
            Either.Left(DomainErrorFactory(errorCode = -2))
        }
    }
}

package com.test.data.source.local

import com.test.common.DomainErrorFactory
import com.test.common.Either

import com.test.data.db.IMovieLocal
import com.test.data.db.TopRatedEntity
import com.test.data.db.UpcomingEntity
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieLocalDataSourceImpl @Inject constructor(private val local: IMovieLocal) :
    MovieLocalDataSource {
    override suspend fun getUpcoming(): Either<DomainErrorFactory, List<UpcomingEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { local.selectUpcoming() }

            when {
                response.isNotEmpty() -> {
                    Either.Right(response)
                }
                else -> {
                    Either.Left(DomainErrorFactory(errorCode = -2))
                }
            }
        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory())
        }

    override suspend fun insertUpcoming(upcomingEntity: List<UpcomingEntity>) {
        local.transactionUpcoming(upcomingEntity)
    }

    override suspend fun getTopRated(): Either<DomainErrorFactory, List<TopRatedEntity>> =
        try {
            val response = withContext(Dispatchers.IO) { local.selectTopRated()}
            when {
                response.isNotEmpty() -> {
                    Either.Right(response)
                }
                else -> {
                    Either.Left(DomainErrorFactory(errorCode = -2))
                }
            }
        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory())
        }

    override suspend fun insertTopRated(topRatedEntity: List<TopRatedEntity>) {
        local.transactionTopRated(topRatedEntity)
    }
}

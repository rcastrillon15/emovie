package com.test.data.source.remote

import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.data.apiservice.IMovieService
import com.test.data.response.detail.DetailResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.UpcomingResponse
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val service: IMovieService) :
    MovieRemoteDataSource {

    override suspend fun getUpcoming(page: Int): Either<DomainErrorFactory, UpcomingResponse> =
        try {
            val response = service.getUpcoming(page = page)
            when {
                response.isSuccessful -> {
                    Either.Right(checkNotNull(response.body()))
                }
                else -> {
                    Either.Left(DomainErrorFactory(response.code()))
                }
            }
        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun getTopRated(page: Int): Either<DomainErrorFactory, TopRatedResponse> =
        try {
            val response = service.getTopRated(page = page)

            when {
                response.isSuccessful -> {
                    Either.Right(checkNotNull(response.body()))
                }
                else -> {
                    Either.Left(DomainErrorFactory(response.code()))
                }
            }

        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun getDetail(movieId: Int): Either<DomainErrorFactory, DetailResponse> =
        try {
            val response = service.getDetail(movieId = movieId)
            when {
                response.isSuccessful -> {
                    Either.Right(checkNotNull(response.body()))
                }
                else -> {
                    Either.Left(DomainErrorFactory(response.code()))
                }
            }
        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory(errorCode = exception.hashCode()))
        }

    override suspend fun getTrailer(movieId: Int): Either<DomainErrorFactory, TrailerResponse> =
        try {
            val response = service.getTrailer(movieId = movieId)
            if (response.isSuccessful) {
                Either.Right(checkNotNull(response.body()))
            } else {
                Either.Left(DomainErrorFactory(response.code()))
            }
        } catch (exception: Exception) {
            Either.Left(DomainErrorFactory(errorCode = exception.hashCode()))
        }
}

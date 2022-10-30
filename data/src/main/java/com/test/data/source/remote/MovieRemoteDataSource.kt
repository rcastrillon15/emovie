package com.test.data.source.remote

import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.data.response.detail.DetailResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.UpcomingResponse

interface MovieRemoteDataSource {
    suspend fun getUpcoming(page:Int): Either<DomainErrorFactory, UpcomingResponse>

    suspend fun getTopRated(page:Int): Either<DomainErrorFactory, TopRatedResponse>

    suspend fun getDetail(movieId:Int): Either<DomainErrorFactory, DetailResponse>

    suspend fun getTrailer(movieId:Int): Either<DomainErrorFactory, TrailerResponse>
}

package com.test.domain.repositories

import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.domain.models.DetailModel
import com.test.domain.models.TopRatedModel
import com.test.domain.models.UpcomingModel

interface MovieRepository {
    suspend fun getUpcoming(page:Int): Either<DomainErrorFactory, List<UpcomingModel>>

    suspend fun getTopRated(page:Int): Either<DomainErrorFactory, List<TopRatedModel>>

    suspend fun getDetail(movieId:Int): Either<DomainErrorFactory, DetailModel>

    suspend fun getTrailer(movieId:Int): Either<DomainErrorFactory, List<String>?>
}

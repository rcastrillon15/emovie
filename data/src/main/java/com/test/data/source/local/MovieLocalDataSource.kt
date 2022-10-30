package com.test.data.source.local

import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.data.db.TopRatedEntity
import com.test.data.db.UpcomingEntity

interface MovieLocalDataSource {
    suspend fun getUpcoming(): Either<DomainErrorFactory, List<UpcomingEntity>>

    suspend fun insertUpcoming(upcomingEntity: List<UpcomingEntity>)

    suspend fun getTopRated(): Either<DomainErrorFactory, List<TopRatedEntity>>

    suspend fun insertTopRated(topRatedEntity: List<TopRatedEntity>)
}
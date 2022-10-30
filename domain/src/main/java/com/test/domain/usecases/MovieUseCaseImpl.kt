package com.test.domain.usecases

import com.test.common.DomainErrorFactory
import com.test.common.Either
import com.test.domain.models.DetailModel
import com.test.domain.models.TopRatedModel
import com.test.domain.models.UpcomingModel
import com.test.domain.repositories.MovieRepository
import javax.inject.Inject

class MovieUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieUseCase {
    override suspend fun getUpcoming(page: Int): Either<DomainErrorFactory, List<UpcomingModel>> =
        repository.getUpcoming(page = page)

    override suspend fun getTopRated(page: Int): Either<DomainErrorFactory, List<TopRatedModel>> =
        repository.getTopRated(page = page)

    override suspend fun getDetail(movieId: Int): Either<DomainErrorFactory, DetailModel> =
        repository.getDetail(movieId = movieId)

    override suspend fun getTrailer(movieId: Int): Either<DomainErrorFactory, List<String>?> =
        repository.getTrailer(movieId = movieId)
}

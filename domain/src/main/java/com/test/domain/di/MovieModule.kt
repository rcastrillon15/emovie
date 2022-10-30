package com.test.domain.di

import com.test.data.source.local.MovieLocalDataSourceImpl
import com.test.data.source.remote.MovieRemoteDataSourceImpl
import com.test.domain.repositories.MovieRepository
import com.test.domain.repositories.MovieRepositoryImpl
import com.test.domain.usecases.MovieUseCase
import com.test.domain.usecases.MovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {

    @Provides
    fun providerRepository(
        remoteDataSource: MovieRemoteDataSourceImpl,
        localDataSource: MovieLocalDataSourceImpl
    ): MovieRepository = MovieRepositoryImpl(remoteDataSource = remoteDataSource, localDataSource)

    @Provides
    fun providerUseCase(repository: MovieRepository): MovieUseCase = MovieUseCaseImpl(repository)
}

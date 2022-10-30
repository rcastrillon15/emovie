package com.test.data.di

import android.content.Context
import androidx.room.Room
import com.test.data.db.AppDatabase
import com.test.data.db.IMovieLocal
import com.test.common.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: AppDatabase): IMovieLocal {
        return appDatabase.iMovieLocal()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }
}

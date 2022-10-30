package com.test.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UpcomingEntity::class, TopRatedEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun iMovieLocal(): IMovieLocal
}

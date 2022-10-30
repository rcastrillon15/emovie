package com.test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface IMovieLocal {
    @Transaction
    suspend fun transactionUpcoming(entity: List<UpcomingEntity>){
        deleteUpcoming()
        insertUpcoming(entity)
    }

    @Query("SELECT * FROM upcoming")
    fun selectUpcoming(): List<UpcomingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcoming(entity: List<UpcomingEntity>)

    @Query("DELETE FROM upcoming")
    fun deleteUpcoming()

    @Transaction
    suspend fun transactionTopRated(entity: List<TopRatedEntity>){
        deleteTopRated()
        insertTopRated(entity)
    }

    @Query("SELECT * FROM topRated")
    fun selectTopRated(): List<TopRatedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRated(entity: List<TopRatedEntity>)

    @Query("DELETE FROM topRated")
    fun deleteTopRated()

}
package com.test.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topRated")
data class TopRatedEntity (
    @PrimaryKey(autoGenerate = true)
    val _idGenerated:Int = 0,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "backdropPath")
    val backdrop_path: String,
    @ColumnInfo(name = "releaseDate")
    val release_date: String,
    @ColumnInfo(name = "originalLanguage")
    val original_language: String,
    @ColumnInfo(name = "originalTitle")
    val original_title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "voteAverage")
    val vote_average: Double
)

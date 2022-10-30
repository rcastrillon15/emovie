package com.test.domain.models

data class UpcomingModel(
    val id: Int = 0,
    val backdrop_path: String = "",
    val release_date: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val vote_average: Double = 0.0
)

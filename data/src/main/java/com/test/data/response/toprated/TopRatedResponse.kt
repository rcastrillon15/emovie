package com.test.data.response.toprated

data class TopRatedResponse(
    val results: List<ResultTopRatedResponse>,
)

data class ResultTopRatedResponse(
    val id: Int,
    val backdrop_path: String,
    val release_date: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val vote_average: Double
)

package com.test.data.response.upcoming

data class UpcomingResponse(
    val results: List<ResultResponse> = listOf(),
)

data class ResultResponse(
    val id: Int = 0,
    val backdrop_path: String = "",
    val release_date: String = "",
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val vote_average: Double = 0.0
)

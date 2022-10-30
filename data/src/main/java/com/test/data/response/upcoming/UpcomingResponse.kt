package com.test.data.response.upcoming

data class UpcomingResponse(
    val results: List<ResultResponse>,
)

data class ResultResponse(
    val id: Int,
    val backdrop_path: String,
    val release_date: String,
    val original_language: String,
    val original_title: String,
    val overview: String
)

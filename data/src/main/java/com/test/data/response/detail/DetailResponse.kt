package com.test.data.response.detail

data class DetailResponse(
    val id: Int,
    val release_date: String,
    val original_language: String,
    val vote_average: Double,
    val genres: List<GenreResponse>,
    val title: String,
    val overview: String,
    val backdrop_path: String
)

data class GenreResponse(
    val id: Int,
    val name: String
)

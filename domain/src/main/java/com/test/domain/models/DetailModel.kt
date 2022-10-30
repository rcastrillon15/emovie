package com.test.domain.models

data class DetailModel(
    val id: Int = 0,
    val release_date: String = "",
    val original_language: String = "",
    val vote_average: Double = 0.0,
    val genres: List<GenreModel> = listOf(),
    val title: String = "",
    val overview: String = "",
    val backdrop_path: String = "",
    val urlTrailer: String? = ""
)

data class GenreModel(
    val id: Int = 0,
    val name: String = ""
)

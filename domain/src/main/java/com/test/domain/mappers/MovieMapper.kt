package com.test.domain.mappers

import com.test.common.Constants.IMAGE_URL
import com.test.data.db.TopRatedEntity
import com.test.data.db.UpcomingEntity
import com.test.data.response.detail.DetailResponse
import com.test.data.response.detail.GenreResponse
import com.test.data.response.toprated.ResultTopRatedResponse
import com.test.data.response.upcoming.ResultResponse
import com.test.domain.models.DetailModel
import com.test.domain.models.GenreModel
import com.test.domain.models.Movie
import com.test.domain.models.TopRatedModel
import com.test.domain.models.UpcomingModel

fun ResultResponse.toUpcomingModel() = UpcomingModel(
        id =  id,
        backdrop_path = IMAGE_URL.plus(backdrop_path),
        original_language = original_language,
        release_date = release_date,
        original_title = original_title,
        overview = overview
)

fun DetailResponse.toDetailModel() = DetailModel(
    id = id,
    release_date = release_date,
    original_language = original_language,
    vote_average = vote_average,
    genres = genres.map(GenreResponse::toGenreModel),
    title = title,
    overview = overview,
    backdrop_path = IMAGE_URL.plus(backdrop_path)
)

fun GenreResponse.toGenreModel() = GenreModel(
    id = id,
    name = name
)

fun ResultTopRatedResponse.toTopRatedModel() = TopRatedModel(
    id = id,
    backdrop_path = IMAGE_URL.plus(backdrop_path),
    original_language = original_language,
    release_date = release_date,
    original_title = original_title,
    overview = overview,
    vote_average = vote_average
)

fun UpcomingModel.toMovie() = Movie(
    id = id,
    backdrop_path = IMAGE_URL.plus(backdrop_path),
    original_language = original_language,
    release_date = release_date,
    original_title = original_title,
    overview = overview,
    vote_average = vote_average
)

fun TopRatedModel.toMovie() = Movie(
    id = id,
    backdrop_path = IMAGE_URL.plus(backdrop_path),
    original_language = original_language,
    release_date = release_date,
    original_title = original_title,
    overview = overview,
    vote_average = vote_average
)

fun ResultResponse.toUpcomingEntity() = UpcomingEntity(
    id = id,
    backdrop_path = IMAGE_URL.plus(backdrop_path),
    original_language = original_language,
    release_date = release_date,
    original_title = original_title,
    overview = overview
)


fun UpcomingEntity.toUpcomingModel() = UpcomingModel(
        id = id,
        backdrop_path = IMAGE_URL.plus(backdrop_path),
        original_language = original_language,
        release_date = release_date,
        original_title = original_title,
        overview = overview
)


fun ResultTopRatedResponse.toTopRatedEntity() = TopRatedEntity(
    id = id,
    backdrop_path = IMAGE_URL.plus(backdrop_path),
    original_language = original_language,
    release_date = release_date,
    original_title = original_title,
    overview = overview,
    vote_average = vote_average
)

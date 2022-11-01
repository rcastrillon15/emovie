package com.test.domain

import com.test.common.Constants
import com.test.data.db.TopRatedEntity
import com.test.data.db.UpcomingEntity
import com.test.data.response.detail.DetailResponse
import com.test.data.response.detail.GenreResponse
import com.test.data.response.toprated.ResultTopRatedResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.Result
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.ResultResponse
import com.test.data.response.upcoming.UpcomingResponse
import com.test.domain.models.DetailModel
import com.test.domain.models.GenreModel
import com.test.domain.models.TopRatedModel
import com.test.domain.models.UpcomingModel

fun getUpcomingResponse() = UpcomingResponse(
    listOf(
        ResultResponse(
            id = 123456,
            backdrop_path = "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
            original_language = "en",
            release_date = "2022-10-06",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity."
        )
    )
)

fun getComingModel() = listOf(
    UpcomingModel(
        id = 123456,
        backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
        original_language = "en",
        release_date = "2022-10-06",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity."
    )
)

fun getUpcomingEntityDummy() = listOf(
    UpcomingEntity(
        id = 123456,
        backdrop_path = "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
        original_language = "en",
        release_date = "2022-10-06",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity."
    )
)

fun topRatedResponseDummy() = TopRatedResponse(
    listOf(
        ResultTopRatedResponse(
            id = 667556,
            backdrop_path ="/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
            release_date = "2022-10-06",
            original_language = "en",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity.",
            vote_average = 8.5
        )
    )
)


fun topRatedEntityDummy() =
    listOf(
        TopRatedEntity(
            id = 667556,
            backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
            release_date = "2022-10-06",
            original_language = "en",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity.",
            vote_average = 8.5
        )
    )

fun getTopRatedModelDummy() = listOf(
    TopRatedModel(
        id = 667556,
        backdrop_path = Constants.IMAGE_URL.plus("/y5Z0WesTjvn59jP6yo459eUsbli.jpg"),
        release_date = "2022-10-06",
        original_language = "en",
        original_title = "Terrifier 2",
        overview = "After being resurrected by a sinister entity.",
        vote_average = 8.5
    )
)

fun detailResponseDummy() = DetailResponse(
    id = 454353,
    release_date = "2021-12-15",
    original_language = "en",
    vote_average = 8.029,
    genres = listOf(GenreResponse(id = 28, name = "Action")),
    title = "Spider-Man: No Way Home",
    overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.",
    backdrop_path = "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg"
)

fun detailModelDummy() = DetailModel(
    id = 454353,
    release_date = "2021-12-15",
    original_language = "en",
    vote_average = 8.029,
    genres = listOf(GenreModel(id = 28, name = "Action")),
    title = "Spider-Man: No Way Home",
    overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.",
    backdrop_path = Constants.IMAGE_URL.plus("/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg")
)

fun getTrailerResponseDummy() = TrailerResponse(listOf(Result(key = "Uty1B1GuO7E")))
package com.test.data.source.remote

import com.test.data.response.detail.DetailResponse
import com.test.data.response.detail.GenreResponse
import com.test.data.response.toprated.ResultTopRatedResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.Result
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.ResultResponse
import com.test.data.response.upcoming.UpcomingResponse

object MovieRemoteDummy {

    fun upcomingResponseDummy() = UpcomingResponse(
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

    fun topRatedResponseDummy() = TopRatedResponse(
        listOf(
            ResultTopRatedResponse(
                id = 13,
                backdrop_path = "/67HggiWaP9ZLv5sPYmyRV37yAJM.jpg",
                release_date = "1994-06-23",
                original_language = "en",
                original_title = "Forrest Gump",
                overview = "A man with a low IQ has accomplished great things in his life and been present during significant historic events—in each case, far exceeding what anyone imagined he could do. But despite all he has achieved, his one true love eludes him.",
                vote_average = 8.5
            )
        )
    )

    fun detailResponseDummy() = DetailResponse(
        id = 634649,
        release_date = "2021-12-15",
        original_language = "en",
        vote_average = 8.029,
        genres = listOf(GenreResponse(id = 28, name = "Action")),
        title = "Spider-Man: No Way Home",
        overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero.",
        backdrop_path = "/14QbnygCuTO0vl7CAFmPf1fgZfV.jpg"
    )

    fun trailerResponseDummy() = TrailerResponse(listOf(Result(key = "Uty1B1GuO7E")))

}

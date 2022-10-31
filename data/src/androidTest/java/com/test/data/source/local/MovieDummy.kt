package com.test.data.source.local

import com.test.data.db.TopRatedEntity
import com.test.data.db.UpcomingEntity

object MovieDummy {
    fun upComingDummy() = listOf(
        UpcomingEntity(
            id = 663712,
            backdrop_path = "/y5Z0WesTjvn59jP6yo459eUsbli.jpg",
            original_language = "en",
            release_date = "2022-10-06",
            original_title = "Terrifier 2",
            overview = "After being resurrected by a sinister entity."
        )
    )

    fun topRatedDummy() = listOf(TopRatedEntity(
        id = 663712,
        backdrop_path = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
        original_language = "ji",
        release_date = "2022-08-11",
        original_title = "Fall",
        overview = "For best friends Becky and Hunter, life is all about conquering fears and pushing limits",
        vote_average = 7.4
    ))
}

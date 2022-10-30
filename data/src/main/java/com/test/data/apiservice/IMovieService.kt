package com.test.data.apiservice

import com.test.common.Constants.API_KEY
import com.test.common.Constants.LANGUAGE
import com.test.common.Constants.TOP_RATED
import com.test.common.Constants.UPCOMING
import com.test.common.Constants.VIDEOS
import com.test.data.response.detail.DetailResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieService {
    @GET(UPCOMING)
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<UpcomingResponse>

    @GET("{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ):Response<DetailResponse>

    @GET(TOP_RATED)
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<TopRatedResponse>

    @GET("{movie_id}/${VIDEOS}")
    suspend fun getTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ):Response<TrailerResponse>
}

package com.test.data.source

import com.test.common.Either
import com.test.data.response.detail.DetailResponse
import com.test.data.response.toprated.TopRatedResponse
import com.test.data.response.trailer.TrailerResponse
import com.test.data.response.upcoming.UpcomingResponse

fun UpcomingResponse.toEiterRight() = Either.Right(this)

fun TopRatedResponse.toEiterRight() = Either.Right(this)

fun DetailResponse.toEiterRight() = Either.Right(this)

fun TrailerResponse.toEiterRight() = Either.Right(this)

package com.test.data.response.trailer

data class TrailerResponse(
    val results: List<Result>?
)

data class Result(
    val key: String
)
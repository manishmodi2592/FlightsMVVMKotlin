package com.test.flighttestapp.models.response

data class MainResponseData(
    val `data`: Data,
    val errors: List<Any>,
    val success: Boolean
)
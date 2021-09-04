package com.test.flighttestapp.models.response

data class CancellationCharges(
    val details: Details,
    val name: String,
    val value: Int
)
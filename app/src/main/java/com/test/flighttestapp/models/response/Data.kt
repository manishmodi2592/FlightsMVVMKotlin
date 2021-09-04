package com.test.flighttestapp.models.response

data class Data(
    val completed: Int,
    val done: Boolean,
    val flights: List<Flight>,
    val sid: String
)
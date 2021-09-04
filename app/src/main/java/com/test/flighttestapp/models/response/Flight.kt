package com.test.flighttestapp.models.response

data class Flight(
    val display_group_id: Int,
    val qid: String,
    val results: Results,
    val vcode: String
)
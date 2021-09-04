package com.test.flighttestapp.models

data class ReqData(
    val limit: Int,
    val location: String,
    val offset: Int,
    val radius: Int,
    val sort_by: String,
    val term: String
)
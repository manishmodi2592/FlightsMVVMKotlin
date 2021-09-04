package com.test.flighttestapp.models.response

data class Results(
    val alMaster: AlMaster,
    val f: List<F>,
    val j: List<J>,
    val j_count: Int
)
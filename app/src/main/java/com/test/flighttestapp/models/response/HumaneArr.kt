package com.test.flighttestapp.models.response

data class HumaneArr(
    val al: Al,
    val ap: Ap,
    val cc: Int,
    val co: List<List<Co>>,
    val eq: Any,
    val nonref: Int,
    val ovngt_flight: Int,
    val ovngt_layover: Int,
    val stp: Int
)
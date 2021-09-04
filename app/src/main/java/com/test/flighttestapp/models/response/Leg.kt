package com.test.flighttestapp.models.response

data class Leg(
    val ad: String,
    val al: List<String>,
    val all_ap: List<String>,
    val ap: List<String>,
    val at: String,
    val dd: String,
    val dt: String,
    val flights: List<FlightX>,
    val lfk: String,
    val lid: String,
    val loap: List<String>,
    val lott: List<Int>,
    val stp: String,
    val tt: String,
    val ttl: List<String>
)
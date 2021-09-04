package com.test.flighttestapp.models.response

data class F(
    val al: List<String>,
    val ap: List<String>,
    val ar_dt: ArDt,
    val at: At,
    val cityap: Cityap,
    val cityap_n: CityapN,
    val dep_dt: DepDt,
    val destination_tz: DestinationTz,
    val dt: Dt,
    val eq: List<String>,
    val fares: List<String>,
    val fares_type: List<FaresType>,
    val fq: Fq,
    val loap: List<String>,
    val lott: Lott,
    val multi_al: Int,
    val nearbydisclaimer: List<Boolean>,
    val origin_tz: OriginTz,
    val pr: Pr,
    val stp: List<String>,
    val tt: Tt,
    val vcode: List<String>
)
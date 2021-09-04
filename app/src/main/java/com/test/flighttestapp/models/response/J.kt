package com.test.flighttestapp.models.response

data class J(
    val FareTypeName: String,
    val ad: String,
    val al: List<String>,
    val ap: List<String>,
    val asdasd: String,
    val at: String,
    val brand_id: String,
    val cc: String,
    val coa: Int,
    val coat: String,
    val cop: Int,
    val copt: String,
    val cot: Int,
    val cott: String,
    val dd: String,
    val display_seat: Boolean,
    val dsp_noshow: Int,
    val dt: String,
    val eqt: Any,
    val fare: Fare,
    val fare_type: String,
    val farebasis: String,
    val farepr: Float,
    val fcc: Any,
    val fk: String,
    val fsr: Int,
    val hmne_prms: List<List<HmnePrm>>,
    val humane_arr: HumaneArr,
    val humane_price: HumanePrice,
    val humane_score: Int,
    val id: String,
    val iic: Boolean,
    val is_lcc: Int,
    val leg: List<Leg>,
    val lg: Int,
    val llow: Int,
    val llowt: String,
    val loap: List<String>,
    val lott: List<Any>,
    val otherfares: Boolean,
    val ovgtf: Int,
    val ovgtlo: Int,
    val ovngt: Int,
    val ovngtt: String,
    val pricingsolution_key: String,
    val qid: Any,
    val red: Int,
    val redt: String,
    val rfd_plcy: RfdPlcy,
    val seats: String,
    val slo: Int,
    val slot: String,
    val stp: String,
    val tt: List<Long>,
    val vendor: String
)
package com.test.flighttestapp.models.response

data class Fare(
    val BF: BF,
    val cancellation_charges: CancellationCharges,
    val grand_total: GrandTotal,
    val gross_fare: GrossFare,
    val net_effective_fare: NetEffectiveFare,
    val net_fare: NetFare,
    val rescheduling_charges: ReschedulingCharges,
    val taxes: Taxes,
    val taxes_sort_order: String,
    val total_payable_now: TotalPayableNow
)
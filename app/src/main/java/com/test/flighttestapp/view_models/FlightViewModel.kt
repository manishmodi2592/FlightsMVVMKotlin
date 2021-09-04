package com.test.flighttestapp.view_models

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.test.flighttestapp.lib.secToTimeInHrMin
import com.test.flighttestapp.lib.toInteger
import com.test.flighttestapp.lib.toRupees
import com.test.flighttestapp.models.response.AlMaster
import com.test.flighttestapp.models.response.J
import java.util.*

class FlightViewModel(var airLineMaster: AlMaster? = null, val flight: J) : ViewModel() {

    val src = ObservableField<String>()
    val dest = ObservableField<String>()
    val logoBg = ObservableField<String>()
    val alName = ObservableField<String>()
    val deptTime = ObservableField<String>()
    val arrTime = ObservableField<String>()
    val stops = ObservableField<String>()
    val fTime = ObservableField<String>()
    val amount = ObservableField<String>()


    fun setRowData() {
        var alDet: Pair<String?, String?>? = null
        when (flight.vendor.toUpperCase(Locale.ROOT)) {
            "6E" -> {
                alDet = Pair(airLineMaster?.`6E`?.bgcolor, airLineMaster?.`6E`?.name)
            }
            "AI" -> {
                alDet = Pair(airLineMaster?.AI?.bgcolor, airLineMaster?.AI?.name)
            }
            "UK" -> {
                alDet = Pair(airLineMaster?.UK?.bgcolor, airLineMaster?.UK?.name)
            }
        }
        alDet?.apply {
            logoBg.set(this.first)
            alName.set(this.second)
        }

        deptTime.set(flight.dt)
        arrTime.set(flight.at)
        src.set(flight.ap[0])
        dest.set(flight.ap[1])
        amount.set(flight.farepr.toString().toRupees())
        fTime.set(flight.tt[0].secToTimeInHrMin())
        when (flight.stp.toInteger()) {
            0 -> stops.set("Non Stop")
            else -> stops.set("${flight.stp} Stop")
        }
    }
}
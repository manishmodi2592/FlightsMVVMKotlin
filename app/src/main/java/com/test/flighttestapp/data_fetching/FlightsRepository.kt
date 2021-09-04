package com.test.flighttestapp.data_fetching

import android.content.Context
import com.test.flighttestapp.lib.AppUtils
import com.test.flighttestapp.models.response.MainResponseData

class FlightsRepository(private val context: Context) {

    suspend fun getFlightList(): MainResponseData? {
        return AppUtils.getModelFromAssets(
            context,
            "response_data.json",
            MainResponseData::class.java
        )
    }

}
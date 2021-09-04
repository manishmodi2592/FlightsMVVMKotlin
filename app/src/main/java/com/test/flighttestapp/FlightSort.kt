package com.test.flighttestapp

import com.test.flighttestapp.models.response.J

class FlightSort(val flights: List<J>) : SortingInterface<J> {

    override suspend fun sortByPrice(): List<J> {
        return flights.sortedBy { it.farepr }
    }

    override suspend fun sortByDuration(): List<J> {
        return flights.sortedBy { it.tt[0] }
    }

    override suspend fun sortByDeparture(): List<J> {
        return flights.sortedBy { it.dt }
    }

    override suspend fun sortByArrival(): List<J> {
        return flights.sortedBy { it.at }
    }
}
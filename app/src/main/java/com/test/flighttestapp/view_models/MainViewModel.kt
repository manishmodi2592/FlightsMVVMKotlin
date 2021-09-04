package com.test.flighttestapp.view_models

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.flighttestapp.FlightSort
import com.test.flighttestapp.SortingEnum
import com.test.flighttestapp.data_fetching.FlightsRepository
import com.test.flighttestapp.data_fetching.Response
import com.test.flighttestapp.lib.listOfField
import com.test.flighttestapp.lib.toddMMM
import com.test.flighttestapp.models.response.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var airLineMaster: AlMaster? = null
    private var flightList: List<J>? = null
    private var flightListPriceFiltered: List<J>? = null
    private var mainResponse: MainResponseData? = null
    var sortApplied: SortingEnum = SortingEnum.PRICE
    var fromPriceSelected: Float = 0F
    var toPriceSelected: Float = 0F

    private var flightSort: FlightSort? = null

    val flightNotFound = "No Flights Found"

    val src = ObservableField<String>()
    val dest = ObservableField<String>()
    val depDate = ObservableField<String>()

    private val mutableLiveDataResponse: MutableLiveData<Response<List<J>>> by lazy { MutableLiveData() }
    val responseLiveData: LiveData<Response<List<J>>>
        get() = mutableLiveDataResponse

    private val mutableLiveDataSort: MutableLiveData<Response<List<J>>> by lazy { MutableLiveData() }
    val sortLiveData: LiveData<Response<List<J>>>
        get() = mutableLiveDataSort

    fun fetchFlightData(flightsRepository: FlightsRepository) {
        viewModelScope.launch {
            mutableLiveDataSort.value = Response.Loading()
            mainResponse = flightsRepository.getFlightList()
            if (mainResponse == null || !mainResponse!!.success) {
                mutableLiveDataSort.postValue(Response.Failure(flightNotFound))
            } else {
                mainResponse?.data?.flights?.get(0)?.results?.apply {
                    airLineMaster = this.alMaster
                    flightList = this.j
                    getMinPrice()?.let {
                        getMaxPrice()?.let { it1 ->
                            setPriceRange(
                                false,
                                it,
                                it1
                            )
                        }
                    }
                    src.set(flightList!![0].ap[0])
                    dest.set(flightList!![0].ap[1])
                    depDate.set(flightList!![0].dd.toddMMM())
                    applySorting(sortApplied)
                }
                //mutableLiveDataSort.postValue(Response.Success(mainResponse!!))
            }

        }
    }


    fun applySorting(sortingEnum: SortingEnum) {
        sortApplied = sortingEnum
        viewModelScope.launch {
            mutableLiveDataResponse.postValue(Response.Loading())
            flightSort = flightListPriceFiltered?.let { FlightSort(it) }
            var list: List<J> = listOf()
            when (sortingEnum) {
                SortingEnum.PRICE -> {
                    flightSort?.let {
                        list = it.sortByPrice()
                    }
                }
                SortingEnum.DEPARTURE -> {
                    flightSort?.let {
                        list = it.sortByDeparture()
                    }
                }
                SortingEnum.ARRIVAL -> {
                    flightSort?.let {
                        list = it.sortByArrival()
                    }
                }
                SortingEnum.DURATION -> {
                    flightSort?.let {
                        list = it.sortByDuration()
                    }
                }
            }
            mutableLiveDataResponse.postValue(Response.Success(list))
        }
    }

    fun getMinPrice(): Float? {
        return flightList?.minOf { it.farepr }
    }

    fun getMaxPrice(): Float? {
        return flightList?.maxOf { it.farepr }
    }

    fun getPriceList(): MutableList<Float>? {
        return flightListPriceFiltered?.listOfField(J::farepr)
    }

    fun setPriceRange(isChanged: Boolean? = false, valueFrom: Float, valueTo: Float) {
        fromPriceSelected = valueFrom
        toPriceSelected = valueTo

        flightListPriceFiltered =
            flightList?.filter { it.farepr in valueFrom..valueTo }

        if (isChanged == true) {
            flightListPriceFiltered?.apply {
                if (this.isNotEmpty()) {
                    applySorting(sortApplied)
                } else {
                    mutableLiveDataResponse.value = Response.Success(this)
                    mutableLiveDataResponse.postValue(Response.Failure(flightNotFound))
                }
            }
        }
    }
}

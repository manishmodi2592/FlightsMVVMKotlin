package com.test.flighttestapp.retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiInterface {

    /*@GET("/weather")
    suspend fun getCurrentDayWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<CurrentDayWeatherMainModel>*/

    @GET
    fun getData(
        @Url url: String,
        @QueryMap appid: Map<String, String>,
    ): Call<ResponseBody>

}
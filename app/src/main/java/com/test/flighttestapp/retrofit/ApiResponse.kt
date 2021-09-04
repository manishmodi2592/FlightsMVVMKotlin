package com.test.flighttestapp.retrofit

interface ApiResponse {
    fun <T> onSuccess(response: T)
    fun <T> onFailure(error: T)
}
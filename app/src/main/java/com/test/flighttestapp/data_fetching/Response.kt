package com.test.flighttestapp.data_fetching

sealed class Response<T>(val response: T? = null, val error: String? = "") {
    class Success<T>(response: T) : Response<T>(response)
    class Failure<T>(error: String) : Response<T>(null, error)
    class Loading<T> : Response<T>()
}

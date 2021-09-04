package com.test.flighttestapp

interface SortingInterface<T> {
    public suspend fun sortByPrice(): List<T>
    public suspend fun sortByDuration(): List<T>
    public suspend fun sortByDeparture(): List<T>
    public suspend fun sortByArrival(): List<T>
}
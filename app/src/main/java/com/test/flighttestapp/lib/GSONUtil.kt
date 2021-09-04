package com.test.flighttestapp.lib

import com.google.gson.Gson

class GSONUtil {
    companion object {
        fun <T> fromJson(reader: String?, classOfT: Class<T>?): T {
            return Gson().fromJson(reader, classOfT)
        }
    }
}
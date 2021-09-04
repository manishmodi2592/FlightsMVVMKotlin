package com.test.flighttestapp.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitClient {

    const val MainServer = " https://api.yelp.com/v3/"

    const val AppId = "fae7190d7e6433ec3a45285ffcf55c86"


    private val retrofitClient: Retrofit.Builder by lazy {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(Level.BODY)

        fun interceptor() = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val request = original.newBuilder()
                request.addHeader("Authorization", "")
                return chain.proceed(request.build())
            }
        }

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)
        okhttpClient.addInterceptor(interceptor())

        Retrofit.Builder()
            .baseUrl(MainServer)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}

package com.test.flighttestapp.retrofit

import android.os.Build
import androidx.annotation.RequiresApi
import com.test.flighttestapp.lib.AppUtils
import com.test.flighttestapp.lib.GSONUtil
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class GenericAPIRespository(val api : String, val requestObj : Objects, response : Class<T>, )
class GenericAPIHelper {
    fun getData(
        url: String,
        reqBody: Any?,
        c: Class<*>,
        apiResponse: ApiResponse
    ) {
        val reqMap: MutableMap<String, String> = AppUtils.pojoToMap(reqBody)
        reqMap["appid"] = RetrofitClient.AppId
        val call: Call<ResponseBody> = RetrofitClient.apiInterface.getData(
            url,
            reqMap
        )

        call.enqueue(object : Callback<ResponseBody?> {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                apiResponse.onSuccess(GSONUtil.fromJson(response.body()?.string(), c))
            }

            override fun onFailure(
                call: Call<ResponseBody?>,
                t: Throwable
            ) {
                apiResponse.onFailure(t)
            }
        })
    }
}
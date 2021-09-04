package com.test.flighttestapp.lib

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.IOException
import kotlin.collections.HashMap

class AppUtils {
    companion object {
        fun pojoToMap(obj: Any?): MutableMap<String, String> {
            var map: HashMap<String, String>? = HashMap()
            try {
                val gson = GsonBuilder().create()
                val json = gson.toJson(obj)
                map = Gson().fromJson<HashMap<String, String>>(json, HashMap::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return map ?: HashMap()
        }


        fun <T> getModelFromAssets(
            context: Context,
            fileName: String?,
            modelClass: Class<T>?
        ): T? {
            val jsonString: String = try {
                val `is` = context.assets.open(fileName!!)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, charset("UTF-8"))
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
            return GSONUtil.fromJson(jsonString, modelClass)
        }

    }
}
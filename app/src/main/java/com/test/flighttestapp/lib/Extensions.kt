@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "UNCHECKED_CAST")

package com.test.flighttestapp.lib

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import kotlin.reflect.KProperty1

fun String?.toInteger(): Int {
    return try {
        this?.toInt() ?: 0
    } catch (e: Exception) {
        0
    }
}

fun String?.toRupees(): String {
    val amt = DecimalFormat("##,##,##0").format(this?.toDouble())
    return "₹ $amt"
}

fun Long.secToTimeInHrMin(): String {
    return try {
        String.format(
            "%dh %dm", this / 3600, (this / 60) % 60
        )
    } catch (e: Exception) {
        ""
    }
}

inline fun <reified T, Y> List<T>.listOfField(property: KProperty1<T, Y?>): MutableList<Y> {
    val yy = ArrayList<Y>()
    this.forEach { t: T ->
        yy.add(property.get(t) as Y)
    }
    return yy
}


@SuppressLint("SimpleDateFormat")
fun String.toddMMM(): String {
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("dd MMM")
    return formatter.format(parser.parse(this))
}

@BindingAdapter("bgColor")
fun setBgColor(view: View, color: String?) {
    color?.apply { view.setBackgroundColor(Color.parseColor(color)) }
}
package com.test.flighttestapp.lib

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


class PopupView<DataBinding : ViewDataBinding> constructor(
    val layout: Int
) {
    private var dataBinding: DataBinding? = null

    @SuppressLint("RtlHardcoded")
    fun showPopupWindow(anchor: View): DataBinding {
        val inflater = LayoutInflater.from(anchor.context)
        dataBinding = DataBindingUtil.inflate(inflater, layout, anchor.parent as ViewGroup, false)
        val contentView = dataBinding!!.root.apply {
            measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )
        }
        //dataBinding!!.setVariable(BR.model, modelType)
        PopupWindow(
            contentView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).also { popupWindow ->

            popupWindow.isOutsideTouchable = true

            val location = locateView(anchor)
            popupWindow.showAtLocation(
                anchor,
                Gravity.TOP or Gravity.LEFT,
                location!!.left,
                location.top
            )
        }
        return dataBinding!!
    }

    private fun locateView(v: View?): Rect? {
        val locInt = IntArray(2)
        if (v == null) return null
        try {
            v.getLocationOnScreen(locInt)
        } catch (npe: NullPointerException) {
            //Happens when the view doesn't exist on screen anymore.
            return null
        }
        val location = Rect()
        location.right = locInt[0]
        location.top = locInt[1] + v.height / 4
        location.left = location.right + v.width / 2
        location.bottom = location.top + v.height
        return location
    }
}
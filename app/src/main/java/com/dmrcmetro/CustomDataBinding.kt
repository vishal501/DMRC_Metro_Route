package com.dmrcmetro

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter


class CustomDataBinding {
    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("touchme")
    fun setViewOnTouch(view: TextView, listener: View.OnTouchListener?) {
        view.setOnTouchListener(listener)
    }

}
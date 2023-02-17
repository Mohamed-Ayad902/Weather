package com.example.weather.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weather.R

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun ImageView.loadImage(link: String) {
    Glide.with(context).load(link).placeholder(R.drawable.logo).into(this)
}

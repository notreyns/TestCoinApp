package com.example.rei.testcoinapp.view.extension

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(
    context: Context,
    imageUrl: String?
) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}
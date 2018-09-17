package com.assignment.kindred.extension

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.assignment.kindred.R
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(this)
}
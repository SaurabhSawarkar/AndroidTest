package com.assignment.kindred.extension

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.assignment.kindred.KindredApplication
import com.assignment.kindred.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(this)
}

@BindingAdapter("backgroundUrl")
fun View.setBackground(url: String?) {
    val view = this
    Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).into(object : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            view.background = BitmapDrawable(KindredApplication.instance.resources, bitmap)
        }
    })
}
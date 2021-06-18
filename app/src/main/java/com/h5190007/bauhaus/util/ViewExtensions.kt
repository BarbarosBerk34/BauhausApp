package com.h5190007.bauhaus

import android.util.Patterns
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.h5190007.bauhaus.util.Constants

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun CharSequence?.isNotEmptyText() = this.toString().replace(Constants.SPACE_REGEX.toRegex(), "") != ""

fun ImageView.downloadAndShowImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}



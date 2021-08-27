package com.ambrozy.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ambrozy.ui.R
import com.squareup.picasso.Picasso

class ImageViewBindings() {
  @BindingAdapter("imageUrl")
  fun ImageView.bindUrl(url: String) {
    if (url.isEmpty()) {
      this.setImageDrawable(context.getDrawable(R.drawable.ic_android_black_24dp))
    } else {
      Picasso
        .get()
        .load(url)
        .placeholder(R.drawable.ic_android_black_24dp)
        .into(this)
    }
  }
}
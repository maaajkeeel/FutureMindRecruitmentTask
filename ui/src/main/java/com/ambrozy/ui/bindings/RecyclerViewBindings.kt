package com.ambrozy.ui.bindings

import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ambrozy.ui.views.ItemDecorator

class RecyclerViewBindings {
  @BindingAdapter(
    "decoratorVerticalMargin",
    "decoratorHorizontalMargin",
    "orientationHorizontal",
  )
  fun RecyclerView.bindListDecorator(
    verticalMargin: Float,
    horizontalMargin: Float,
    orientationHorizontal: Boolean
  ) {
    this.addItemDecoration(
      ItemDecorator(
        context,
        LinearLayout.VERTICAL.takeIf { orientationHorizontal } ?: LinearLayout.HORIZONTAL,
        horizontalMargin,
        verticalMargin
      )
    )
  }
}
package com.ambrozy.ui.views

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class ItemDecorator(
  context: Context,
  orientation: Int,
  private val horizontalMargin: Float,
  private val verticalMargin: Float
) : DividerItemDecoration(context, orientation) {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    outRect.apply {
      right = horizontalMargin.toInt()
      left = horizontalMargin.toInt()
      top = verticalMargin.toInt()
      bottom = verticalMargin.toInt()
    }
  }
}
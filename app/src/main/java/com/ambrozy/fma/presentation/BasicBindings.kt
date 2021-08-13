package com.ambrozy.fma.presentation

import androidx.databinding.DataBindingComponent
import com.ambrozy.ui.bindings.ImageViewBindings
import com.ambrozy.ui.bindings.RecyclerViewBindings

class DataBindingComponents : DataBindingComponent {
  override fun getImageViewBindings(): ImageViewBindings = ImageViewBindings()
  override fun getRecyclerViewBindings(): RecyclerViewBindings = RecyclerViewBindings()
}

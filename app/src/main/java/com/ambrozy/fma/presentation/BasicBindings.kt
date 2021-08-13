package com.ambrozy.fma.presentation

import androidx.databinding.DataBindingComponent
import com.ambrozy.ui.bindings.ImageViewBindings

class DataBindingComponents : DataBindingComponent {
  override fun getImageViewBindings(): ImageViewBindings = ImageViewBindings()
}

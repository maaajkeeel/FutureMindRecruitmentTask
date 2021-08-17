package com.ambrozy.fma.presentation

import androidx.databinding.DataBindingComponent
import com.ambrozy.ui.bindings.ImageViewBindings
import com.ambrozy.ui.bindings.RecyclerViewBindings
import com.ambrozy.ui.bindings.TextViewBindings
import com.ambrozy.ui.bindings.WebViewBindings

class DataBindingComponents : DataBindingComponent {
  override fun getWebViewBindings(): WebViewBindings = WebViewBindings()
  override fun getImageViewBindings(): ImageViewBindings = ImageViewBindings()
  override fun getRecyclerViewBindings(): RecyclerViewBindings = RecyclerViewBindings()
  override fun getTextViewBindings(): TextViewBindings = TextViewBindings()
}

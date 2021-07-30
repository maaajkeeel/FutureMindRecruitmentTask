package com.ambrozy.fma.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ambrozy.fma.BR

abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel>
  (@LayoutRes private val layout: Int, private val viewModelClass: Class<VM>) : AppCompatActivity() {
  val viewModel: VM by lazy {
    ViewModelProvider(this).get(viewModelClass)
  }
  val binding: VDB? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<VDB>(this, layout)
    binding.apply {
      lifecycleOwner = this@BaseActivity
      setVariable(BR.viewModel, viewModel)
    }
    lifecycle.addObserver(viewModel)
  }
}
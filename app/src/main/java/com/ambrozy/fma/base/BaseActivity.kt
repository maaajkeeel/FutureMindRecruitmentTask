package com.ambrozy.fma.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.ambrozy.fma.BR

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>
  (@LayoutRes private val layout: Int) : AppCompatActivity() {
  val viewModel: VM by lazy {
    ViewModelProvider(this@BaseActivity).get(viewModel.javaClass)
  }

  override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
    DataBindingUtil.setContentView<VB>(this, layout).apply {
      lifecycleOwner = this@BaseActivity
      setVariable(BR.viewModel, viewModel)
    }
    lifecycle.addObserver(viewModel)

    return super.onCreateView(name, context, attrs)
  }
}
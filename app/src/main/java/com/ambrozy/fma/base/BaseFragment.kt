package com.ambrozy.fma.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ambrozy.fma.BR

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel>
  (@LayoutRes private val layout: Int) : Fragment() {
  val viewModel: VM by lazy {
    ViewModelProvider(this@BaseFragment).get(viewModel.javaClass)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(layout, container, false)

    DataBindingUtil.bind<VB>(view)?.apply {
      setVariable(BR.viewModel, viewModel)
      lifecycleOwner = viewLifecycleOwner
    }

    lifecycle.addObserver(viewModel)

    return super.onCreateView(inflater, container, savedInstanceState)
  }
}
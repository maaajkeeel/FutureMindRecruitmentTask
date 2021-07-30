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

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel>
  (@LayoutRes private val layout: Int, private val viewModelClass: Class<VM>) : Fragment() {
  val viewModel: VM by lazy {
    ViewModelProvider(this).get(viewModelClass)
  }
  var binding: VDB? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(layout, container, false)
    binding = DataBindingUtil.bind<VDB>(view)?.apply {
      setVariable(BR.viewModel, viewModel)
      lifecycleOwner = viewLifecycleOwner
    }

    lifecycle.addObserver(viewModel)

    return view
  }
}
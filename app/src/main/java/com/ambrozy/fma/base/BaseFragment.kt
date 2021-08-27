package com.ambrozy.fma.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ambrozy.fma.BR
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseFragment<VDB : ViewDataBinding, T : BaseEvent, VM : BaseViewModel<T>>
  (@LayoutRes private val layout: Int, private val viewModelClass: Class<VM>) : Fragment() {
  val viewModel: VM by lazy {
    ViewModelProvider(this)[viewModelClass]
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

  abstract fun onEventOccurred(event: T)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.eventsFlow.onEach {
          onEventOccurred(it)
        }.launchIn(this)
      }
    }
  }
}
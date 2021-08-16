package com.ambrozy.fma.presentation.redirection

import com.ambrozy.fma.R
import com.ambrozy.fma.base.BaseFragment
import com.ambrozy.fma.databinding.RedirectionFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RedirectionFragment :
  BaseFragment<RedirectionFragmentLayoutBinding, RedirectionScreenEvent, RedirectionViewModel>(
    R.layout.redirection_fragment_layout,
    RedirectionViewModel::class.java
  ) {
  override fun onEventOccurred(event: RedirectionScreenEvent) {
//    TODO("Not yet implemented")
  }
}
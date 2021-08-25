package com.ambrozy.fma.presentation.main

import androidx.navigation.fragment.findNavController
import com.ambrozy.fma.R
import com.ambrozy.fma.base.BaseFragment
import com.ambrozy.fma.databinding.MainFragmentLayoutBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
  BaseFragment<MainFragmentLayoutBinding, Event, MainViewModel>(
    R.layout.main_fragment_layout,
    MainViewModel::class.java
  ) {

  override fun onEventOccurred(event: Event) {
    when (event) {
      is Event.NavigateToWebViewScreen -> {
        findNavController().navigate(MainFragmentDirections.openRedirectionFragment(event.redirectionLink))
      }
      is Event.ShowToast -> {
        Snackbar.make(this.requireView(), event.text, Snackbar.LENGTH_SHORT).show()
      }
    }
  }
}
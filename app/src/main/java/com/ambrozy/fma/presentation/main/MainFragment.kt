package com.ambrozy.fma.presentation.main

import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ambrozy.fma.R
import com.ambrozy.fma.TabletNavDirections
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
        if (resources.getBoolean(R.bool.isTablet)) {
          binding?.tabletContainerView?.findNavController()
            ?.navigate(TabletNavDirections.openRedirectionFragment(event.redirectionLink))
        } else {
          findNavController().navigate(MainFragmentDirections.openRedirectionFragment(event.redirectionLink))
        }
      }
      is Event.ShowToast -> {
        Snackbar.make(this.requireView(), event.text, Snackbar.LENGTH_SHORT).show()
      }
    }
  }
}
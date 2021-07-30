package com.ambrozy.fma.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ambrozy.fma.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
  val text: LiveData<String> = MutableLiveData("Hello world")

  init {
    Timber.d("MainViewModel has started!")
  }
}
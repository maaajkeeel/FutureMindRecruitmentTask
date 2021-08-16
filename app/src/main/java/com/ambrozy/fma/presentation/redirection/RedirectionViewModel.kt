package com.ambrozy.fma.presentation.redirection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.ambrozy.fma.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RedirectionViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : BaseViewModel<RedirectionScreenEvent>() {
  val redirectionLink: LiveData<String> = MutableLiveData(savedStateHandle.get("redirectionLink"))
}

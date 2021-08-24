package com.ambrozy.fma.presentation.main

import com.ambrozy.fma.base.BaseEvent

sealed class Event : BaseEvent() {
  data class NavigateToWebViewScreen(val redirectionLink: String) : Event()
  data class ShowToast(val text: String) : Event()
}
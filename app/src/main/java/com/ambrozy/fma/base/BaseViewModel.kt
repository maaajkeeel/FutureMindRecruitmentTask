package com.ambrozy.fma.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<T : BaseEvent> : ViewModel(), LifecycleObserver {
  val eventChannel = Channel<T>(Channel.BUFFERED)
  val eventsFlow = eventChannel.receiveAsFlow()
}
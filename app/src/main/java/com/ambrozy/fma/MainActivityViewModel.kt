package com.ambrozy.fma

import com.ambrozy.fma.base.BaseEvent
import com.ambrozy.fma.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainActivityEvent : BaseEvent()

@HiltViewModel
class MainActivityViewModel @Inject constructor() : BaseViewModel<MainActivityEvent>()
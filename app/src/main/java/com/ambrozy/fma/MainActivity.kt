package com.ambrozy.fma

import com.ambrozy.fma.base.BaseActivity
import com.ambrozy.fma.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityEvent, MainActivityViewModel>(
  layout = R.layout.activity_main,
  viewModelClass = MainActivityViewModel::class.java
)
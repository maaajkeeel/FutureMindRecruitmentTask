package com.ambrozy.fma.presentation

import com.ambrozy.fma.R
import com.ambrozy.fma.base.BaseFragment
import com.ambrozy.fma.databinding.MainFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentLayoutBinding, MainViewModel>(R.layout.main_fragment_layout)
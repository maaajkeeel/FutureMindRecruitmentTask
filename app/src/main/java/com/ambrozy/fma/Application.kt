package com.ambrozy.fma

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.ambrozy.fma.presentation.DataBindingComponents
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class FutureMindApp : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    DataBindingUtil.setDefaultComponent(DataBindingComponents())
  }
}
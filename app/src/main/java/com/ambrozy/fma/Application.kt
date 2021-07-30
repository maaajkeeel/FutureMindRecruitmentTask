package com.ambrozy.fma

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class FutureMindApp : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    Timber.d("BLA BLA BLA IN ONCREATE APPLICATION")
  }
}
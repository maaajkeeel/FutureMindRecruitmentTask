package com.ambrozy.ui.bindings

import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ambrozy.ui.views.ItemDecorator

class RecyclerViewBindings {
  @BindingAdapter(
    "decoratorVerticalMargin",
    "decoratorHorizontalMargin",
    "orientationHorizontal",
  )
  fun RecyclerView.bindListDecorator(
    verticalMargin: Float,
    horizontalMargin: Float,
    orientationHorizontal: Boolean
  ) {
    this.addItemDecoration(
      ItemDecorator(
        context,
        LinearLayout.VERTICAL.takeIf { orientationHorizontal } ?: LinearLayout.HORIZONTAL,
        horizontalMargin,
        verticalMargin
      )
    )
  }

  @BindingAdapter("attachSnapHelper")
  fun RecyclerView.bindSnapHelper(attach: Boolean) {
    if (attach) {
      LinearSnapHelper().attachToRecyclerView(this)
    }
  }
}

class WebViewBindings() {
  @BindingAdapter("enableJS")
  fun WebView.bindEnableJs(enable: Boolean) {
    settings.javaScriptEnabled = enable
  }

  @BindingAdapter("preventRedirections")
  fun WebView.bindPreventRedirections(prevent: Boolean) {
    webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        loadUrl(request?.url.toString())
        return false
      }
    }
    webChromeClient = WebChromeClient()
  }
}
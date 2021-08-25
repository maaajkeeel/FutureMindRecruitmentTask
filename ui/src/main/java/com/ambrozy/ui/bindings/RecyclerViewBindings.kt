package com.ambrozy.ui.bindings

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.ambrozy.ui.views.ItemDecorator
import org.joda.time.DateTime

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

class TextViewBindings() {
  @BindingAdapter("date")
  fun TextView.bindDate(date: Long) {
    text = DateTime(date).toString(DATE_TIME_PATTERN)
  }

  companion object {
    const val DATE_TIME_PATTERN = "dd.MM.yyyy"
  }
}

class BasicBindings() {
  @BindingAdapter("visible")
  fun View.bindVisible(visible: Boolean) {
    visibility = VISIBLE.takeIf { visible } ?: GONE
  }
}
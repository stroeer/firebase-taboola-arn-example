package expo.modules.taboola

import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import com.taboola.android.listeners.TBLWebListener
import com.taboola.android.Taboola
import com.taboola.android.tblweb.TBLWebUnit
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.views.ExpoView

class ExpoTaboolaView(context: Context, appContext: AppContext) : ExpoView(context, appContext) {
  internal val webView = WebView(context).also {

    it.settings.javaScriptEnabled = true
    it.settings.domStorageEnabled = true
    it.settings.databaseEnabled = true

    it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    it.webViewClient = object : WebViewClient() {
      override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        val extraHeaders = mapOf("view-mode" to "radio-android")
        view.loadUrl(url, extraHeaders)
        return true
      }
    }
    addView(it)

    val tblWebPage = Taboola.getWebPage()
    val tblWebUnit = tblWebPage.build(it, object : TBLWebListener() {})
    val extraProperties = hashMapOf("darkMode" to "true")
    tblWebUnit.setUnitExtraProperties(extraProperties)

    WebView.setWebContentsDebuggingEnabled(true)

    val extraHeaders = mapOf("view-mode" to "radio-android")
    it.loadUrl("https://www.t-online.de/native/radio-android/", extraHeaders)
  }
}
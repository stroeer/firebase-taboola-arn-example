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
    it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    it.webViewClient = object : WebViewClient() {}


    val tblWebPage = Taboola.getWebPage().build(it, object : TBLWebListener() {})

    addView(it)

    it.loadUrl("https://www.t-online.de/")
  }
}
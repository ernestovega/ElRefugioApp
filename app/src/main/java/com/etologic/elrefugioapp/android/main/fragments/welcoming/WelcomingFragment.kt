package com.etologic.elrefugioapp.android.main.fragments.welcoming

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.R.layout
import com.etologic.elrefugioapp.android.main.activity.MainBaseFragment

class WelcomingFragment : MainBaseFragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.e_welcoming_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadWebView(view)
    }
    
    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView(view: View) {
        val webView: WebView = view.findViewById(R.id.wvWelcoming)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url.let { view?.loadUrl(url) }
                return true
            }
        }
        webView.loadUrl(getString(R.string.url_welcoming))
    }
}

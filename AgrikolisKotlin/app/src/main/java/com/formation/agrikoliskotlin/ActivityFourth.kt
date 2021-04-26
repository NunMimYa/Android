package com.formation.agrikoliskotlin

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity


class ActivityFourth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)

        val myWebView = WebView(this)
        val mWebSettings: WebSettings = myWebView.getSettings()
//        mWebSettings.javaScriptEnabled = true
//        mWebSettings.setSupportZoom(true)
        mWebSettings.setDomStorageEnabled(true)
        setContentView(myWebView)
        myWebView.loadUrl("https://agrikolis.com/")
    }
}
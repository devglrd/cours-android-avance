package com.example.myapplication.ui.fragments

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main_webview.*

class ActivityWebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_webview)
        webview.webViewClient = WebViewClient()
        webview.loadUrl(intent.getStringExtra("url"))
    }
}
package com.example.androidnavigationxbug

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.util.Date

class WebViewFragment: Fragment(R.layout.fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webview = view.findViewById<WebView>(R.id.webview)
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (view != null && url != null) {
                    view.loadUrl(url)
                    return false
                }
                return true
            }
        }
        val query = arguments?.getString("query")
        if (query != null) {
            webview.loadUrl("https://google.com/search?q=" + query)
        } else {
            webview.loadUrl("https://www.google.com")
        }

        val button = view.findViewById<AppCompatButton>(R.id.button)
        button.setOnClickListener {
            val navController = findNavController()
            val query = Date().time.toString()
            var args = Bundle()
            args.putString("query", query)
            navController.navigate(R.id.webview_fragment, args)
        }
    }
}
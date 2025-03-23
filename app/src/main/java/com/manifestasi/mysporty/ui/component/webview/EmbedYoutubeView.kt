package com.manifestasi.mysporty.ui.component.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun EmbedYoutubeView(url: String){
    AndroidView(
        modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(22.dp)),
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                loadUrl(url)
            }
        },
        update = { it.loadUrl(url) }
    )
}

@Composable
@Preview(showBackground = true)
fun EmbedYoutubeViewPreview(){
    MySportyTheme {
        EmbedYoutubeView("https://youtube.com")
    }
}
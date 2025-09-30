package com.santimattius.kmp.entertainment.feature.homepage

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.santimattius.kmp.entertainment.core.ui.components.WebView

@Composable
fun HomeWebPageScreen(
    url: String
) {
    WebView(
        modifier = Modifier.fillMaxSize(),
        url = url
    )
}
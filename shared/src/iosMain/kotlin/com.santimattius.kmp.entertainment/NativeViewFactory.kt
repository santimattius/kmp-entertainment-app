package com.santimattius.kmp.entertainment

import platform.UIKit.UIViewController

interface NativeViewFactory {

    fun createWebView(urlString: String): UIViewController
}
package com.santimattius.kmp.entertainment

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController

val LocalNativeViewFactory = staticCompositionLocalOf<NativeViewFactory> {
    error("LocalNativeViewFactory not provided")
}

actual fun getPlatformName(): String = "iOS"

fun MainViewController(
    nativeViewFactory: NativeViewFactory
) = ComposeUIViewController {
    CompositionLocalProvider(LocalNativeViewFactory provides nativeViewFactory) {
        App()
    }
}


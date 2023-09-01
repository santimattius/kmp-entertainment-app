package com.santimattius.kmp.entertainment

import androidx.compose.ui.window.ComposeUIViewController
import com.santimattius.kmp.entertainment.App

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App() }
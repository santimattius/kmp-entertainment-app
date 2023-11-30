package com.santimattius.kmp.entertainment

import androidx.compose.ui.window.ComposeUIViewController
import com.santimattius.kmp.entertainment.core.db.DriverFactory

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App(DriverFactory()) }
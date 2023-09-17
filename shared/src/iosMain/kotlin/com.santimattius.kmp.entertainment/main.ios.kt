package com.santimattius.kmp.entertainment

import androidx.compose.ui.window.ComposeUIViewController
import com.santimattius.kmp.entertainment.App
import moe.tlaster.precompose.PreComposeApplication

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = PreComposeApplication { App() }
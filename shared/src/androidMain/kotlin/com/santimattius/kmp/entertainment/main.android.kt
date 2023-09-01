package com.santimattius.kmp.entertainment

import androidx.compose.runtime.Composable
import com.santimattius.kmp.entertainment.App

actual fun getPlatformName(): String = "Android"

@Composable fun MainView() = App()

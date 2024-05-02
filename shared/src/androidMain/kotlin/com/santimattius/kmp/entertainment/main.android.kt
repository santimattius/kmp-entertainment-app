package com.santimattius.kmp.entertainment

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.santimattius.kmp.entertainment.core.db.DriverFactory

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(context: Context = LocalContext.current){
    App(DriverFactory(context))
}

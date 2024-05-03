package com.santimattius.kmp.entertainment

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.santimattius.kmp.entertainment.core.db.RoomFactory
import com.santimattius.kmp.entertainment.core.db.getRoomDatabase

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(context: Context = LocalContext.current) {
    App(getRoomDatabase(RoomFactory(context).create()))
}

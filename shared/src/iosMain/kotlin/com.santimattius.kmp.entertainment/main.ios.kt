package com.santimattius.kmp.entertainment

import androidx.compose.ui.window.ComposeUIViewController
import com.santimattius.kmp.entertainment.core.db.RoomFactory
import com.santimattius.kmp.entertainment.core.db.getRoomDatabase

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App(getRoomDatabase(RoomFactory().create())) }
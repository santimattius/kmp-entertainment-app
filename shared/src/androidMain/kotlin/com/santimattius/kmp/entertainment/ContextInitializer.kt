package com.santimattius.kmp.entertainment

import android.content.Context
import androidx.startup.Initializer

internal var applicationContext: Context? = null
    private set

class ContextInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        applicationContext = context.applicationContext
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
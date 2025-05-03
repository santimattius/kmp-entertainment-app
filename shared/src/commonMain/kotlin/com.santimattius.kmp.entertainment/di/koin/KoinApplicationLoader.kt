package com.santimattius.kmp.entertainment.di.koin

import androidx.compose.runtime.RememberObserver
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform
import org.koin.mp.KoinPlatform.stopKoin

@KoinInternalApi
class KoinApplicationLoader(
    val koinApplication: KoinApplication,
) : RememberObserver {

    var koin: Koin? = null

    init {
        start()
    }

    override fun onAbandoned() {
        stop()
    }

    override fun onForgotten() {
        stop()
    }

    override fun onRemembered() {
        start()
    }

    private fun start() {
        val currentKoin = KoinPlatform.getKoinOrNull()
        if (currentKoin == null) {
            try {
                koin = startKoin(koinApplication).koin
                koin!!.logger.debug("$this -> started Koin Application $koinApplication")
            } catch (e: Exception) {
                error("Can't start Koin from Compose context - $e")
            }
        } else {
            koin = currentKoin
        }
    }

    private fun stop() {
        koin = null
        KoinPlatform.getKoinOrNull()?.logger?.debug("$this -> stop Koin Application $koinApplication")
        stopKoin()
    }
}
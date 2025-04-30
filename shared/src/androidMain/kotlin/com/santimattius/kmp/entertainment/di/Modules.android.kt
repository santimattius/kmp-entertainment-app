package com.santimattius.kmp.entertainment.di

import android.content.Context
import com.santimattius.kmp.entertainment.applicationContext
import com.santimattius.kmp.entertainment.core.db.RoomFactory
import com.santimattius.kmp.entertainment.core.db.TMDBDataBase
import com.santimattius.kmp.entertainment.core.db.getRoomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        factory<Context> {
            val context = applicationContext ?: run {
                throw IllegalArgumentException("Application context is null")
            }
            context
        }
        single<TMDBDataBase> { getRoomDatabase(RoomFactory(get()).create()) }
    }
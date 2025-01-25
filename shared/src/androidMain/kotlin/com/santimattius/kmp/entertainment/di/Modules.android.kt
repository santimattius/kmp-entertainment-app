package com.santimattius.kmp.entertainment.di

import com.santimattius.kmp.entertainment.core.db.RoomFactory
import com.santimattius.kmp.entertainment.core.db.TMDBDataBase
import com.santimattius.kmp.entertainment.core.db.getRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<TMDBDataBase> {
            getRoomDatabase(
                builder = RoomFactory(
                    context = androidContext()
                ).create()
            )
        }
    }
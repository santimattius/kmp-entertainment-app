package com.santimattius.kmp.entertainment.core.db

import app.cash.sqldelight.db.SqlDriver
import com.santimattius.kmp.entertainment.AppDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): AppDatabase {
    val driver = driverFactory.createDriver()

    return AppDatabase(driver)
}
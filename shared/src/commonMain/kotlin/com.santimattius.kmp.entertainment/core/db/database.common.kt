package com.santimattius.kmp.entertainment.core.db

import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO


expect class RoomFactory {

    fun create(): RoomDatabase.Builder<TMDBDataBase>
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<TMDBDataBase>
): TMDBDataBase {
    return builder
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
